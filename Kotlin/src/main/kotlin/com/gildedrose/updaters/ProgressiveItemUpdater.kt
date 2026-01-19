package com.gildedrose.updaters

import com.gildedrose.Item

/**
 * ProgressiveItemUpdater lowers sell date and increase the quality by progressive pairs
 * where the number of days left until sell by date indicates the amount to increase in quality
 */
class ProgressiveItemUpdater(
    qualityAmountPerDay: Int,
    minQuality: Int = DEFAULT_MIN_QUALITY,
    maxQuality: Int = DEFAULT_MAX_QUALITY,
    vararg amountPerDays: Pair<Int, Int>
) : SimpleItemUpdater(qualityAmountPerDay, minQuality, maxQuality) {

    private val qualityAmountPerDays: List<Pair<Int, Int>> = amountPerDays
        .sortedBy { (key, _) -> key }
        .map { (key, value) -> Pair(key, value) }
        .toList()

    override fun updateQualityValue(item: Item) = with(item) {
        quality = alignQuality(
            when {
                sellIn <= 0 -> minQuality // drop to min
                else -> quality + (
                        qualityAmountPerDays
                            .firstOrNull { sellIn <= it.first }
                            ?.second
                            ?: qualityAmountPerDay
                        )
            }
        )
    }

}
package com.gildedrose.updaters

import com.gildedrose.Item
import kotlin.math.max
import kotlin.math.min

/**
 * SimpleItemUpdater lowers sell date and increase the quality by a fixed amount.
 */
open class SimpleItemUpdater(
    protected val qualityAmountPerDay: Int = -1,
    protected val minQuality: Int = DEFAULT_MIN_QUALITY,
    protected val maxQuality: Int = DEFAULT_MAX_QUALITY,
) : ItemUpdater {

    companion object {
        const val DEFAULT_MIN_QUALITY: Int = 0
        const val DEFAULT_MAX_QUALITY: Int = 50
    }

    init {
        require(minQuality < maxQuality) { "minQuality=$minQuality should be less than maxQuality=$maxQuality" }
    }

    override fun updateQuality(item: Item) {
        updateQualityValue(item)
        updateSellInDate(item)
    }

    protected open fun updateQualityValue(item: Item) = with(item) {
        quality = alignQuality(
            when {
                // once the sell by date has passed, quality changes twice as fast
                sellIn <= 0 ->
                    quality + qualityAmountPerDay * 2

                else ->
                    quality + qualityAmountPerDay
            }
        )
    }

    // Limits new quality value: min <= quality <= max
    protected open fun alignQuality(quality: Int): Int =
        max(minQuality, min(maxQuality, quality))


    // Decreases sell date by one day
    protected open fun updateSellInDate(item: Item) = item.sellIn--

}


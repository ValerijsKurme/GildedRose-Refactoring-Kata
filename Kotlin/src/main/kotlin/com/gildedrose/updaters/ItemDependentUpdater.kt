package com.gildedrose.updaters

import com.gildedrose.Item

/**
 * ItemDependentUpdater directs the update of an item to a specific updater depending on the current state of the item.
 * The selection of a specific updater is based on the list of predicate-updater pairs.
 */
class ItemDependentUpdater(
    private val defaultUpdater: ItemUpdater,
    vararg dependedUpdaters: Pair<(Item) -> Boolean, ItemUpdater>
) : ItemUpdater {

    private val predicate2Updaters = dependedUpdaters.toList()

    override fun updateQuality(item: Item) {
        getUpdaterForItem(item).updateQuality(item)
    }

    private fun getUpdaterForItem(item: Item): ItemUpdater =
        predicate2Updaters
            .filter { it.first.invoke(item) }
            .map { it.second }
            .firstOrNull()
            ?: defaultUpdater

}
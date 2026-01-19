package com.gildedrose

import com.gildedrose.updaters.ItemUpdater
import com.gildedrose.updaters.ItemUpdaterFactory

class GildedRose(val items: List<Item>) {

    private val itemUpdater: ItemUpdater = ItemUpdaterFactory.instance.defaultGildedRoseItemUpdater

    fun updateQuality() = itemUpdater.updateQualities(items)

}


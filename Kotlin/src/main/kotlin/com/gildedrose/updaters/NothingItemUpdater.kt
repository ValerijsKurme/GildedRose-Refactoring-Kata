package com.gildedrose.updaters

import com.gildedrose.Item

/**
 * NothingItemUpdater does nothing, uses when an item never has to be sold and decreases in Quality
 * */
class NothingItemUpdater : ItemUpdater {

    override fun updateQuality(item: Item) {}

}
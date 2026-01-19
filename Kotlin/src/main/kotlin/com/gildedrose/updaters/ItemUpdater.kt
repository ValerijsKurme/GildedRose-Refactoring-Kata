package com.gildedrose.updaters

import com.gildedrose.Item

interface ItemUpdater {

    /** Updates an item at the end of each day **/
    fun updateQuality(item: Item)

    fun updateQualities(items: Collection<Item>) = items.forEach { updateQuality(it) }

}

package com.gildedrose.updaters

class ItemUpdaterFactory private constructor() {

    companion object {
        val instance = ItemUpdaterFactory()
    }

    // Default item updater that meets the Gilded Rose Requirements Specification.
    val defaultGildedRoseItemUpdater: ItemUpdater by lazy {

        val min = 0 // The Quality of an item is never negative
        val max = 50 // The Quality of an item is never more than 50

        ItemDependentUpdater(
            // at the end of each day our system lowers both values for every item
            defaultUpdater = SimpleItemUpdater(-1, min, max),
            Pair(
                { it.name == "Aged Brie" },
                // 'Aged Brie' increases in quality the older it get
                SimpleItemUpdater(1, min, max)
            ),
            Pair(
                { it.name == "Sulfuras, Hand of Ragnaros" },
                // 'Sulfuras' item never has to be sold or decreases in quality
                NothingItemUpdater()
            ),
            Pair(
                { it.name == "Backstage passes to a TAFKAL80ETC concert" },
                /* 'Backstage passes' increases in quality as its SellIn value approaches
                    by 2 when there are 10 days or less, by 3 when there are 5 days or less and by one otherwise */
                ProgressiveItemUpdater(
                    1, min, max, 10 to 2, 5 to 3
                )
            ),
            Pair(
                { it.name == "Conjured Mana Cake" },
                // 'Conjured' degrades in quality twice as fast as normal items
                SimpleItemUpdater(-2, min, max),
            ),
        )
    }

}

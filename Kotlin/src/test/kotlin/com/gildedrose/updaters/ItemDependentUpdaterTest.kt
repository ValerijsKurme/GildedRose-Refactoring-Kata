package com.gildedrose.updaters

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ItemDependentUpdaterTest {

    @Test
    fun `Each item should be updated by specific updater`() {
        val updater = ItemDependentUpdater(
            SimpleItemUpdater(0),
            Pair({ it.name.contains(" 1") }, SimpleItemUpdater(1)),
            Pair({ it.name.contains(" 2") }, SimpleItemUpdater(2)),
        )

        val sellIn = 10
        val quality = 5
        val items = listOf(
            Item("Item 0", sellIn, quality),
            Item("Item 1", sellIn, quality),
            Item("Item 2", sellIn, quality),
            Item("Item 3", sellIn, quality),
        )

        val expected = listOf(
            Triple(items[0].name, sellIn - 1, quality),
            Triple(items[1].name, sellIn - 1, quality + 1),
            Triple(items[2].name, sellIn - 1, quality + 2),
            Triple(items[3].name, sellIn - 1, quality),
        )

        updater.updateQualities(items)

        val actual = items
            .map { Triple(it.name, it.sellIn, it.quality) }
            .toList()

        assertEquals(expected, actual)
    }

}

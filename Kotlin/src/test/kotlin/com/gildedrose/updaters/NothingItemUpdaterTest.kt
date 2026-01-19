package com.gildedrose.updaters

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NothingItemUpdaterTest {

    private val itemName = "legendary item "
    private val updater: ItemUpdater = NothingItemUpdater()

    @ParameterizedTest(name = "quality: {0}")
    @CsvSource("8", "5", "1", "0", "-1", "50", "60")
    fun `Quality should not be updated`(quality: Int) {
        val item = Item(itemName, 2, quality)
        updater.updateQuality(item)
        assertEquals(quality, item.quality)
    }

    @ParameterizedTest(name = "sellIn: {0}")
    @CsvSource("60", "40", "20", "9", "5", "3", "2", "1", "0", "-1", "-10")
    fun `SellIn should not be updated`(sellIn: Int) {
        val item = Item(itemName, sellIn, 5)
        updater.updateQuality(item)
        assertEquals(sellIn, item.sellIn)
    }

}
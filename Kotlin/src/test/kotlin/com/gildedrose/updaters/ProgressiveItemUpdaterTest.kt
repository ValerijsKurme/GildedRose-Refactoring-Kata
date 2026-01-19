package com.gildedrose.updaters

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ProgressiveItemUpdaterTest {

    @ParameterizedTest(name = "sellIn: {0} quality: {1}")
    @CsvSource(
        // should be increased by 1
        "18, 1, 2",
        "8, 31, 32",

        // should be increased by 2
        "7, 32, 34",
        "6, 34, 36",
        "5, 36, 38",
        "4, 38, 40",

        // should be increased by 3
        "3, 40, 43",
        "2, 43, 46",
        "1, 46, 49",
        "1, -1,  2",

        // limit max 50
        "8, 50, 50",
        "4, 49, 50",
        "4, 50, 50",
        "1, 48, 50",
        "1, 49, 50",
        "1, 50, 50",

        // drop to zero
        "0, 50, 0",
        "-1, 50, 0",
        "0, -1, 0",
        "-1, 1, 0"
    )
    fun `Quality should be updated depended on sell day`(sellIn: Int, quality: Int, expected: Int) {
        val updater: ItemUpdater = ProgressiveItemUpdater(
            qualityAmountPerDay = 1,
            minQuality = 0, maxQuality = 50,
            7 to 2, 3 to 3
        )
        val item = Item("backstage passes", sellIn, quality)
        updater.updateQuality(item)
        assertEquals(expected, item.quality)
    }

}
package com.gildedrose.updaters

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SimpleItemUpdaterTest {

    @ParameterizedTest(name = "sellIn: {0} quality: {1}")
    @CsvSource(
        "18, 20, 19",
        "8, 10, 9",
        "7,  9,  8",
        "4,  6,  5",
        "2,  4,  3",
        "1,  3,  2",

        // should be decreased twice
        "0, 10,  8",
        "0,  3,  1",
        "0,  2,  0",
        "-1, 10,  8",
        "-2,  3,  1",
        "-9,  2,  0",

        // quality is never negative
        "0,   0,  0",
        "1,  -5,  0",
        "0,   1,  0",
        "0,  -5,  0",
    )
    fun `Quality should be decreased by one`(sellIn: Int, quality: Int, expected: Int) {
        val updater = SimpleItemUpdater(qualityAmountPerDay = -1, minQuality = 0, maxQuality = 50)
        val item = Item("normal item", sellIn, quality)
        updater.updateQuality(item)
        assertEquals(expected, item.quality)
    }

    @ParameterizedTest(name = "sellIn: {0} quality: {1}")
    @CsvSource(
        "18, 20, 22",
        "8, 10, 12",
        "7,  9, 11",
        "4,  6,  8",
        "2,  4,  6",
        "1,  3,  5",

        // should be increased twice
        "0, 10, 14",
        "0,  3,  7",
        "0,  2,  6",
        "-1, 10, 14",
        "-2,  3,  7",
        "-9,  2,  6",

        // limit max 50
        "0, 49, 50",
        "7, 99, 50",
    )
    fun `Quality should be increased by two`(sellIn: Int, quality: Int, expected: Int) {
        val updater = SimpleItemUpdater(qualityAmountPerDay = 2, minQuality = 0, maxQuality = 50)
        val item = Item("conjured aged brie", sellIn, quality)
        updater.updateQuality(item)
        assertEquals(expected, item.quality)
    }

}
package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `Name is not changed`() {
        val items = listOf(Item("foo", 1, 1))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    @Test
    fun `No items without errors`() {
        val app = GildedRose(listOf())
        app.updateQuality()
    }

    @Test
    fun `toString work as expected`() {
        val item = Item("Item name", -2, 11)
        assertEquals("Item name, -2, 11", item.toString())
    }

}



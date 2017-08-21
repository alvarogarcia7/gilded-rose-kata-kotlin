package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {

    @Test
    fun shouldLowerSellInAndQualityOfItemAtTheEndOfEachDay() {
        val result = updateQualityTo(arrayOf(Item("anItem", 3, 5)))
        assertEquals("anItem", result[0].name)
        assertEquals(2, result[0].sellIn)
        assertEquals(4, result[0].quality)
    }

    @Test fun shouldLowerSellInAndQualityOfAllItemsAtTheEndOfEachDay() {
        val items = arrayOf(
                Item("anItem", 3, 5),
                Item("anItem", 3, 5))
        val app = GildedRose(items)
        app.updateQuality()

        for (item in app.items) {
            assertEquals("anItem", item.name)
            assertEquals(2, item.sellIn)
            assertEquals(4, item.quality)
        }
    }

    @Test
    fun shouldQualityBeNeverNegative() {
        val items = arrayOf(Item("anItem", 10, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("anItem", app.items[0].name)
        assertEquals(9, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

    private fun updateQualityTo(items: Array<Item>): Array<Item> {
        val app = GildedRose(items)
        app.updateQuality()
        return app.items
    }


}



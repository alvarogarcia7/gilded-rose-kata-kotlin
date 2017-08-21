package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {

    @Test
    fun shouldLowerSellInAndQualityOfItemAtTheEndOfEachDay() {
        val items = arrayOf<Item>(Item("anItem", 3, 5))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("anItem", app.items[0].name)
        assertEquals(2, app.items[0].sellIn)
        assertEquals(4, app.items[0].quality)

    }
}



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
        val items = updateQualityTo(arrayOf(
                Item("anItem", 3, 5),
                Item("anItem", 3, 5)))

        for (item in items) {
            assertEquals("anItem", item.name)
            assertEquals(2, item.sellIn)
            assertEquals(4, item.quality)
        }
    }

    @Test
    fun shouldQualityBeNeverNegative() {
        val items = updateQualityTo(arrayOf(Item("anItem", 10, 0)))
        assertEquals("anItem", items[0].name)
        assertEquals(9, items[0].sellIn)
        assertEquals(0, items[0].quality)
    }

    @Test
    fun shouldQualityDecreaseTwiceIfTheItemSellPeriodIsEnded() {
        val items = updateQualityTo(arrayOf(Item("anItem", 0, 2)))
        assertEquals("anItem", items[0].name)
        assertEquals(-1, items[0].sellIn)
        assertEquals(0, items[0].quality)
    }


    @Test
    fun agedBrieActuallyIncreasesWhenADayPassesBy() {
        val items = updateQualityTo(arrayOf(Item("Aged Brie", 1, 2)))
        assertEquals(0, items[0].sellIn)
        assertEquals(3, items[0].quality)
    }

    @Test
    fun agedBrieActuallyIncreasesDoubleFastWhenADayPassesByAndTheSellInHasExpired() {
        val items = updateQualityTo(arrayOf(Item("Aged Brie", 0, 2)))
        assertEquals(-1, items[0].sellIn)
        assertEquals(4, items[0].quality)
    }

    @Test
    fun qualityShouldNotBeLargerThan50() {
        val items = updateQualityTo(arrayOf(Item("Aged Brie", 0, 50)))
        assertEquals(-1, items[0].sellIn)
        assertEquals(50, items[0].quality)
    }

    @Test
    fun sulfurasNeverShouldBeSoldOrDecreasesInQuality() {
        val items = updateQualityTo(arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 5)))
        assertEquals(0, items[0].sellIn)
        assertEquals(5, items[0].quality)
    }

    private fun updateQualityTo(items: Array<Item>): Array<Item> {
        val app = GildedRose(items)
        app.updateQuality()
        return app.items
    }


}



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
    fun sulfurasNeverShouldBeSoldOrDecreasesInQuality_caseExpired() {
        `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(0, 5)
    }

    @Test
    fun sulfurasNeverShouldBeSoldOrDecreasesInQuality_caseNotExpired() {
        `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(1, 2)
    }

    @Test
    fun sulfurasNeverShouldBeSoldOrDecreasesInQuality_caseMaximumNormalQuality() {
        `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(1, 50)
    }

    @Test
    fun sulfurasNeverShouldBeSoldOrDecreasesInQuality_caseMaximumQuality() {
        `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(1, 80)
    }

    @Test
    fun `backstage passes increases quality when SellIn value approaches -  Case Tier 3`() {
        val x = 30
        val items = updateQualityTo(arrayOf(backstagePass(11, x)))
        assertEquals(10, items[0].sellIn)
        assertEquals(x + 1, items[0].quality)
    }

    @Test
    fun `backstage passes increases quality when SellIn value approaches -  Case Tier 2`() {
        val x = 30
        val items = updateQualityTo(arrayOf(backstagePass(10, x)))
        assertEquals(9, items[0].sellIn)
        assertEquals(x + 2, items[0].quality)
    }

    @Test
    fun `backstage passes increases quality when SellIn value approaches -  Case Tier 1`() {
        val x = 30
        val items = updateQualityTo(arrayOf(backstagePass(5,x)))
        assertEquals(4, items[0].sellIn)
        assertEquals(x + 3, items[0].quality)
    }

    @Test
    fun `backstage passes increases quality when SellIn value approaches -  Expired`() {
        val items = updateQualityTo(arrayOf(backstagePass(0,30)))
        assertEquals(-1, items[0].sellIn)
        assertEquals(0, items[0].quality)
    }

    private fun `"Sulfuras", being a legendary item, never has to be sold or decreases in Quality`(sellIn: Int, quality: Int) {
        val items = updateQualityTo(arrayOf(Item("Sulfuras, Hand of Ragnaros", sellIn, quality)))
        assertEquals(sellIn, items[0].sellIn)
        assertEquals(quality, items[0].quality)
    }

    private fun backstagePass(sellIn: Int, quality: Int) = Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)

    private fun updateQualityTo(items: Array<Item>): Array<Item> {
        val app = GildedRose(items)
        app.updateQuality()
        return app.items
    }


}



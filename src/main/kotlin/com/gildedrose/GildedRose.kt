package com.gildedrose


class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            items[i] = ItemFactory.aNew(items[i]).updateQuality().toDTO()
        }
    }

    private fun decreaseQuality(i: Int) {
        if (items[i].quality > 0) {
            items[i].quality = items[i].quality - 1
        }
    }

    private fun increaseQuality(item: Item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1
        }
    }

}


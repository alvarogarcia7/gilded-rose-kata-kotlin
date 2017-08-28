package com.gildedrose


class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            items[i] = ItemFactory.aNew(items[i]).updateQuality().toDTO()
        }
    }

}


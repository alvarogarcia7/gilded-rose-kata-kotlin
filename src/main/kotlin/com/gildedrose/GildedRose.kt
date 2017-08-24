package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                continue
            }
            if ((items[i].name.equals("Aged Brie"))){
                increaseQuality(items[i])
            } else if(items[i].name.equals("Backstage passes to a TAFKAL80ETC " +
                    "concert")) {
                increaseQuality(items[i])

                if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (items[i].sellIn < 11) {
                        increaseQuality(items[i])
                    }

                    if (items[i].sellIn < 6) {
                        increaseQuality(items[i])
                    }
                }
            } else {
                decreaseQuality(i)
            }

            items[i].sellIn = items[i].sellIn - 1

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        decreaseQuality(i)
                    } else {
                        items[i].quality = 0
                    }
                } else {
                    increaseQuality(items[i])
                }
            }
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


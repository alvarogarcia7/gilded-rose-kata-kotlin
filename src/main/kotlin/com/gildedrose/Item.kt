package com.gildedrose

data class Item(var name: String, var sellIn: Int, var quality: Int)

open class MyItem {

    internal var item: Item

    constructor(item: Item){
        this.item = item
    }

    open fun updateQuality(): MyItem {
        if (item.name.equals("Aged Brie")) {

        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseQuality()

            if (item.sellIn < 11) {
                increaseQuality()
            }

            if (item.sellIn < 6) {
                increaseQuality()
            }
            if (item.sellIn < 0) {
                item.quality = 0
            }
        } else {
            decreaseQuality()
            if (item.sellIn < 0) {
                decreaseQuality()
            }
        }

        item.sellIn = item.sellIn - 1

        if (item.sellIn < 0) {
            if (item.name.equals("Aged Brie")) {
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                item.quality = 0
            } else {
                decreaseQuality()
            }
        }

        return this
    }

    fun toDTO(): Item {
        return Item(item.name, item.sellIn, item.quality)
    }

    private fun decreaseQuality() {
        if (item.quality > 0) {
            item.quality = item.quality - 1
        }
    }

    internal fun increaseQuality() {
        if (item.quality < 50) {
            item.quality = item.quality + 1
        }
    }
}

class BackstagePasses(name: Item) : MyItem(name)

class ImmutableItem(item: Item) : MyItem(item) {
    override fun updateQuality():MyItem{
        return this
    }
}

class AgedBrie(item: Item) : MyItem(item) {
    override fun updateQuality(): MyItem {
        increaseQuality()
        if (item.sellIn < 0) {
            increaseQuality()
        }
        item.sellIn = item.sellIn - 1

        if (item.sellIn < 0) {
            increaseQuality()
        }
        return this
    }
}

object ItemFactory {
    fun aNew(item: Item): MyItem {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return ImmutableItem(item)
        } else if (item.name.equals("Aged Brie")) {
            return AgedBrie(item)
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return BackstagePasses(item)
        } else {
            return MyItem(item)
        }
    }
}

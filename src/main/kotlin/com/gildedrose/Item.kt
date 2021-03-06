package com.gildedrose

data class Item(var name: String, var sellIn: Int, var quality: Int)

open class MyItem {

    internal var item: Item

    constructor(item: Item){
        this.item = item
    }

    open fun updateQuality(): MyItem {

        decreaseQuality()
        if (isExpired()) {
            decreaseQuality()
        }

        decreaseSellIn()

        if (isExpired()) {
            decreaseQuality()
        }

        return this
    }

    internal fun decreaseSellIn() {
        item.sellIn = item.sellIn - 1
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

    internal fun isExpired() = item.sellIn < 0
}

class BackstagePasses(name: Item) : MyItem(name) {
    override fun updateQuality(): MyItem {
        increaseQuality()

        if (item.sellIn < 11) {
            increaseQuality()
        }

        if (item.sellIn < 6) {
            increaseQuality()
        }
        if (isExpired()) {
            item.quality = 0
        }

        decreaseSellIn()

        if (isExpired()) {
            item.quality = 0
        }

        return this
    }
}

class ImmutableItem(item: Item) : MyItem(item) {
    override fun updateQuality():MyItem{
        return this
    }
}

class AgedBrie(item: Item) : MyItem(item) {
    override fun updateQuality(): MyItem {
        increaseQuality()
        if (isExpired()) {
            increaseQuality()
        }

        decreaseSellIn()

        if (isExpired()) {
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

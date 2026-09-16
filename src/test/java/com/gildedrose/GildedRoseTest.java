package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseTest {

    @Test
    public void updateQuality_basic() {
        Item[] items = new Item[] { Item.makeItem("naname", 0, 0) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(1, items.length);
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void updateQuality_AgedBrie_negativeSellIn_increasesQuality() {
        Item[] items = new Item[] { Item.makeItem("Aged Brie", 0, 0) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();
        gildedRose.updateQuality();

        assertEquals(1, items.length);
        assertEquals(-2, items[0].sellIn);
        assertEquals(3, items[0].quality);
    }

    @Test
    public void updateQuality_Legendary_qualityAndSellInUnchanged() {
        Item[] items = new Item[] {
            Item.makeItem("Sulfuras, Hand of Ragnaros", 0, 0),
        };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(1, items.length);
        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void updateQuality_Ticket() {
        Item[] items = new Item[] {
            Item.makeItem("Backstage passes to a TAFKAL80ETC concert", 15, 0),
        };
        GildedRose gildedRose = new GildedRose(items);

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertEquals(10, items[0].sellIn);
        assertEquals(5, items[0].quality);

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertEquals(5, items[0].sellIn);
        assertEquals(15, items[0].quality);

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertEquals(0, items[0].sellIn);
        assertEquals(30, items[0].quality);

        gildedRose.updateQuality(); // -1, 33
        gildedRose.updateQuality(); // -2, 0

        assertEquals(-2, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }
}

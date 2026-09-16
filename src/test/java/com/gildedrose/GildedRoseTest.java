package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseTest {

    @Test
    public void test() {
        Item[] items = new Item[] { Item.makeItem("naname", 0, 0) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(1, items.length);
        assertEquals(-1, items[0].sellIn);
        //assertEquals(0, items[0].quality);
    }
}

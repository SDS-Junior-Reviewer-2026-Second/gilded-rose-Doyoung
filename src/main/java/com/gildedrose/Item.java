package com.gildedrose;

public abstract class Item {

    private static final String CONJURED = "Conjured";
    private static final String BACKSTAGE_PASSES =
        "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public abstract void advanceDay();

    public static Item makeItem(String name, int sellIn, int quality) {
        if (name.equals(AGED_BRIE)) {
            return new AgedBrie(name, sellIn, quality);
        } else if (name.equals(BACKSTAGE_PASSES)) {
            return new Ticket(name, sellIn, quality);
        } else if (name.equals(SULFURAS)) {
            return new LegendaryItem(name, sellIn);
        } else if (name.equals(CONJURED)) {
            return new ConjuredItem(name, sellIn, quality);
        } else {
            return new NormalItem(name, sellIn, quality);
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}

class NormalItem extends Item {

    public NormalItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void advanceDay() {
        if (this.sellIn < 0) {
            this.quality = Math.max(this.quality - 2, 0);
        } else {
            this.quality = Math.max(this.quality - 1, 0);
        }

        this.sellIn--;
    }
}

class LegendaryItem extends Item {

    public LegendaryItem(String name, int sellIn) {
        super(name, sellIn, 80);
    }

    @Override
    public void advanceDay() {}
}

class AgedBrie extends Item {

    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void advanceDay() {
        if (this.sellIn < 0) {
            this.quality = Math.min(this.quality + 2, 50);
        } else {
            this.quality = Math.min(this.quality + 1, 50);
        }

        this.sellIn--;
    }
}

class Ticket extends Item {

    public Ticket(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void advanceDay() {
        if (this.sellIn < 0) {
            this.quality = 0;
        } else if (this.sellIn < 6) {
            this.quality = Math.min(this.quality + 3, 50);
        } else if (this.sellIn < 11) {
            this.quality = Math.min(this.quality + 2, 50);
        } else {
            this.quality = Math.min(this.quality + 1, 50);
        }

        this.sellIn--;
    }
}

class ConjuredItem extends Item {

    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void advanceDay() {
        if (this.sellIn < 0) {
            this.quality = Math.max(this.quality - 4, 0);
        } else {
            this.quality = Math.max(this.quality - 2, 0);
        }

        this.sellIn--;
    }
}

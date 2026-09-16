package com.gildedrose;

public abstract class Item {

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
        if (name.equals("Aged Brie")) {
            return new AgedBrie(name, sellIn, quality);
        } else if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return new Ticket(name, sellIn, quality);
        } else if (name.equals("Sulfuras, Hand of Ragnaros")) {
            return new LegendaryItem(name, sellIn);
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
        if (this.quality <= 0) {
            this.sellIn--;
            return;
        }

        if (this.sellIn < 0) {
            this.quality -= 2;
        } else {
            this.quality--;
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
        if (this.quality >= 50) {
            this.sellIn--;
            return;
        }

        if (this.sellIn < 0) {
            this.quality += 2;
        } else {
            this.quality++;
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
            this.quality += 3;
        } else if (this.sellIn < 11) {
            this.quality += 2;
        } else {
            this.quality++;
        }

        this.sellIn--;
    }
}

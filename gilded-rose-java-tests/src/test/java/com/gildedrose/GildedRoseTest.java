package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseTest {

	private GildedRose gildedRose;

    @Test
    public void standard_items_lower_SellIn_and_Quality() {
        createItemWithSellinAndQuality("+5 Dexterity Vest", 10, 10);
        doUpdateQuality();
        verifyItemIs("+5 Dexterity Vest", 9, 9);
    }

    @Test
    public void the_Quality_of_an_item_is_never_negative() throws Exception {
        createItemWithSellinAndQuality("+5 Dexterity Vest", 10, 0);
        doUpdateQuality();
        verifyItemIs("+5 Dexterity Vest", 9, 0);
    }

    @Test
    public void once_the_sell_by_date_has_passed_Quality_degrades_twice_as_fast() throws Exception {
        createItemWithSellinAndQuality("+5 Dexterity Vest", 0, 10);
        doUpdateQuality();
        verifyItemIs("+5 Dexterity Vest", -1, 8);
    }

    @Test
    public void once_the_sell_by_date_has_passed_Quality_degrades_twice_as_fast_but_never_becomes_negative() throws Exception {
        createItemWithSellinAndQuality("+5 Dexterity Vest", 0, 1);
        doUpdateQuality();
        verifyItemIs("+5 Dexterity Vest", -1, 0);
    }

    @Test
    public void aged_Brie__actually_increases_in_Quality_the_older_it_gets() throws Exception {
        createItemWithSellinAndQuality("Aged Brie", 10, 10);
        doUpdateQuality();
        verifyItemIs("Aged Brie", 9, 11);
    }

    @Test
    public void the_Quality_of_an_item_is_never_more_than_50() throws Exception {
        createItemWithSellinAndQuality("Aged Brie", 10, 50);
        doUpdateQuality();
        verifyItemIs("Aged Brie", 9, 50);
    }

    @Test
    public void sulfuras_being_a_legendary_item_never_has_to_be_sold_or_decreases_in_Quality() throws Exception {
        String sulfuras = "Sulfuras, Hand of Ragnaros";
        createItemWithSellinAndQuality(sulfuras, 10, 80);
        doUpdateQuality();
        verifyItemIs(sulfuras, 10, 80);
    }

    @Test
    public void backstage_passes_increases_in_Quality_as_its_SellIn_value_approaches() throws Exception {
    	createBackstagePassWithSellinAndQuality(20, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(19, 11);
    	
    	createBackstagePassWithSellinAndQuality(11, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(10, 11);
    }

    @Test
    public void backstage_passes_with_SellIn_value_less_than_or_equal_to_10_increases_in_quality_by_two () {
        createBackstagePassWithSellinAndQuality(10, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(9, 12);
    	
        createBackstagePassWithSellinAndQuality(9, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(8, 12);
    	
        createBackstagePassWithSellinAndQuality(6, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(5, 12);
    }

    @Test
    public void backstage_passes_with_SellIn_value_less_than_or_equal_to_5_increases_in_quality_by_three () {
        createBackstagePassWithSellinAndQuality(5, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(4, 13);
    	
        createBackstagePassWithSellinAndQuality(4, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(3, 13);
    	
        createBackstagePassWithSellinAndQuality(1, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(0, 13);
    }

    @Test
    public void backstage_passes_with_SellIn_value_less_than_or_equal_to_0_drop_quality_to_0() {
        createBackstagePassWithSellinAndQuality(0, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(-1, 0);
    	
        createBackstagePassWithSellinAndQuality(-1, 10);
    	doUpdateQuality();
    	verifyBackstagePassIs(-2, 0);
    }

    @Test
    public void backstage_passes_quality_never_increases_beyond_50() {
        createBackstagePassWithSellinAndQuality(20, 50);
    	doUpdateQuality();
    	verifyBackstagePassIs(19, 50);
    	
        createBackstagePassWithSellinAndQuality(9, 50);
    	doUpdateQuality();
    	verifyBackstagePassIs(8, 50);
    	
        createBackstagePassWithSellinAndQuality(3, 50);
    	doUpdateQuality();
    	verifyBackstagePassIs(2, 50);
    }

    private void doUpdateQuality() {
        gildedRose.updateQuality();
    }
    
    private void createBackstagePassWithSellinAndQuality(int sellIn, int quality) {
    	 String backstagePass = "Backstage passes to a TAFKAL80ETC concert";
    	 createItemWithSellinAndQuality(backstagePass, sellIn, quality);
    }

    private void createItemWithSellinAndQuality(String name, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        gildedRose = new GildedRose(items);
    }
    
    private void verifyBackstagePassIs(int sellIn, int quality) {
    	String backstagePass = "Backstage passes to a TAFKAL80ETC concert";
    	verifyItemIs(backstagePass, sellIn, quality);
    }

    private void verifyItemIs(String name, int sellIn, int quality) {
        assertEquals(name, gildedRose.items[0].name);
        assertEquals(sellIn, gildedRose.items[0].sellIn);
        assertEquals(quality, gildedRose.items[0].quality);
    }

}

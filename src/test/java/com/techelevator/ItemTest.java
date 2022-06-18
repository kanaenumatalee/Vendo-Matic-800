package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemTest {
    Item item;

    @Before
    public void setup() {
        item = new Item();
    }

    @Test
    public void check_itemIdList_setup() {
        item.itemIdList = new ArrayList<String>();
        item.itemIdList.add("a");
        item.itemIdList.add("b");
        item.itemIdList.add("c");

        String expected = "b";
        Assert.assertEquals("It should return " + expected, expected, item.itemIdList.get(1));
        expected = "c";
        Assert.assertEquals("It should return " + expected, expected, item.itemIdList.get(2));
    }

    @Test
    public void check_itemQuantity_setup() {
        item.itemQuantity = new HashMap<String, Integer>();
        item.itemQuantity.put("a", 1);
        item.itemQuantity.put("b", 2);
        item.itemQuantity.put("c", 3);

        Integer expected = 3;
        Assert.assertEquals("It should return " + expected, expected, item.itemQuantity.get("c"));
    }

    @Test
    public void check_itemsSalesCount_setup() {
        item.itemSalesCount = new HashMap<String, Integer>();
        item.itemSalesCount.put("a", 10);
        item.itemSalesCount.put("b", 20);
        item.itemSalesCount.put("c", 30);

        Integer expected = 30;
        Assert.assertEquals("It should return " + expected, expected, item.itemSalesCount.get("c"));
    }
}

package com.techelevator.view;

import java.math.BigDecimal;

public abstract class Product {

    private String slotNumber;
    private String itemName;
    private BigDecimal cost;
    private int inventoryCount;

    public Product(String slotNumber, String itemName, BigDecimal cost, int inventoryCount) {
        this.slotNumber = slotNumber;
        this.itemName = itemName;
        this.cost = cost;
        this.inventoryCount = 5;
    }

    public int decrementInv() {
        inventoryCount -= 1;
        return inventoryCount;

    }

    public abstract String getResponse();

    public String getSlotNumber() {
        return slotNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }
}





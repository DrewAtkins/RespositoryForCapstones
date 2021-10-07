package com.techelevator.view;

import java.math.BigDecimal;

public abstract class Product {

    private String slotNumber;
    private String itemName;
    private BigDecimal cost;
    private int inventoryCount;

    public Product(String slotNumber, String itemName, BigDecimal cost, int inventoryCount) {
    }

    public abstract String getResponse();
}



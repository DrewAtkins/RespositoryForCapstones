package com.techelevator.view;

import java.math.BigDecimal;

public class Drink extends Product{
    private String response = "Glug Glug, Yum!";

    public Drink (String slotNumber, String itemName, BigDecimal cost, int inventoryCount) {
        super(slotNumber, itemName, cost, inventoryCount);

    }
    @Override
    public String getResponse() {
        return response;

    }
}

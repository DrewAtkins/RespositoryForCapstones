package com.techelevator.view;

import java.math.BigDecimal;

public class Candy extends Product{

    private String response = "Munch Munch, Yum!";

    public Candy (String slotNumber, String itemName, BigDecimal cost, int inventoryCount) {
        super(slotNumber, itemName, cost, inventoryCount);

    }
    @Override
    public String getResponse() {
        return response;

    }
}

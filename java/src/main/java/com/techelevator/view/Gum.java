package com.techelevator.view;

import java.math.BigDecimal;

public class Gum extends Product implements Vendable{
    private String response = "Chew Chew, Yum!";

    public Gum (String slotNumber, String itemName, BigDecimal cost, int inventoryCount) {
        super(slotNumber, itemName, cost, inventoryCount);

    }
    @Override
    public String getResponse() {
        return response;

    }
}

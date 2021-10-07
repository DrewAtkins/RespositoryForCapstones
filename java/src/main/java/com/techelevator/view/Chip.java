package com.techelevator.view;

import java.math.BigDecimal;

public class Chip extends Product{
//    private String slotNumber;
//    private String itemName;
//    private BigDecimal cost;
//    private int inventoryCount = 5;
    private String response = "Crunch Crunch, Yum!";

    public Chip (String slotNumber, String itemName, BigDecimal cost, int inventoryCount) {
        super(slotNumber, itemName, cost, inventoryCount);

        }
    @Override
    public String getResponse() {
        return response;

    }

}


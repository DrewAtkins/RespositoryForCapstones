package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private static List<Product> vendingMachineItems = new ArrayList<Product>();

    private void loadingMachineItems() throws FileNotFoundException {
        File vendItems = new File("vendingmachine.csv");
        try (Scanner readingVendItems = new Scanner(vendItems)) {
            while (readingVendItems.hasNextLine()) {
                String line = readingVendItems.nextLine();
                 vendingMachineItems.add(buildProduct(line));
            }

        }
    }

    public Product buildProduct(String line) throws IllegalArgumentException {
        String[] parts = line.split("|");
        if (parts[3].equals("Chip")) {

            BigDecimal costDecimal = new BigDecimal(parts[2]);
            Chip newChip = new Chip(parts[0], parts[1], costDecimal, 5);
            return newChip;

        } else if (parts[3].equals("Gum")) {
            BigDecimal costDecimal = new BigDecimal(parts[2]);
            Gum newGum = new Gum(parts[0], parts[1], costDecimal, 5);
            return newGum;
        } else if (parts[3].equals("Drink")) {
            BigDecimal costDecimal = new BigDecimal(parts[2]);
            Drink newDrink = new Drink(parts[0], parts[1], costDecimal, 5);
            return newDrink;
        } else if (parts[3].equals("Candy")) {
            BigDecimal costDecimal = new BigDecimal(parts[2]);
            Candy newCandy = new Candy(parts[0], parts[1], costDecimal, 5);
            return newCandy;
        } else {
            throw new IllegalArgumentException("Item doesn't fit into classes");
        }

    }

}

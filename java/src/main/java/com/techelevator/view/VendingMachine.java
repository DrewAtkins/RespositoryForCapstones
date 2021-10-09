package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private List<Product> vendingMachineItems = new ArrayList<Product>();
    BigDecimal balance = new BigDecimal(0);
    BigDecimal change = new BigDecimal(0);
    //coins
    final BigDecimal NICKELS = new BigDecimal(.05);
    final BigDecimal DIMES = new BigDecimal(.10);
    final BigDecimal QUARTERS = new BigDecimal(.25);
    //bills
    public final BigDecimal DOLLAR = new BigDecimal(1.00);
    public final BigDecimal FIVE = new BigDecimal(5.00);
    public final BigDecimal TEN = new BigDecimal(10.00);
    public final BigDecimal TWENTY = new BigDecimal(20.00);

    public VendingMachine() {
        loadingMachineItems();
    }

    //load machine and get info from csv
    private void loadingMachineItems() {
        File vendItems = new File("vendingmachine.csv");
        try (Scanner readingVendItems = new Scanner(vendItems)) {
            while (readingVendItems.hasNextLine()) {
                String line = readingVendItems.nextLine();
                vendingMachineItems.add(buildProduct(line));
                //System.out.println(vendingMachineItems);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    public List<Product> getVendingMachineItems() {
        return vendingMachineItems;
    }

    public Product buildProduct(String line) throws IllegalArgumentException {
        String[] parts = line.split("\\|");


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
            throw new IllegalArgumentException("Item not found");
        }

    }

    //handle money
    public BigDecimal feedMoney(BigDecimal moneyAdded) {
        if (moneyAdded.equals(TWENTY)) {
            balance = balance.add(moneyAdded);
        } else if (moneyAdded.equals(TEN)) {
            balance = balance.add(moneyAdded);
        } else if (moneyAdded.equals(FIVE)) {
            balance = balance.add(moneyAdded);
        } else if (moneyAdded.equals(DOLLAR)) {
            balance = balance.add(moneyAdded);
        }
        return balance;
    }

    public BigDecimal getBalance() {
        System.out.println(balance);
        return balance;
    }

    public BigDecimal returnChange() {
        return change;
    }

    public BigDecimal subtractBalance(BigDecimal itemCost) {
        balance = balance.subtract(itemCost);
        return balance;
    }

    public void getSelection() {
    }


//handle product selection

    public String purchaseProduct() {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = getVendingMachineItems();

        //displayList
        for (Product item : products) {
            System.out.println(item.getSlotNumber() + "|" + item.getItemName() +
                    "|" + item.getCost() + "|" + item.getInventoryCount());
        }
        System.out.println("Please select item by inputting slot location (ex. A1): ");
        String slotLocation = scanner.nextLine();

        Product foundProduct = null;
        for (Product product : products) {
            if (product.getSlotNumber().equals(slotLocation)) {
                foundProduct = product;
            }

        }
        if (foundProduct == null) {
            System.out.println("Invalid Selection!");
            return "Invalid Selection!";
        }


        if (foundProduct.getInventoryCount() > 0) {
            subtractBalance(foundProduct.getCost());
            foundProduct.decrementInv();
            System.out.println(foundProduct.getItemName() + "|" + foundProduct.getCost() + "|" + getBalance() + "|" + foundProduct.getResponse());
        } else {
            System.out.println("Item is out of stock");
        }

        return "Successful purchase";
    }

// add to cart, subtract from inventory count, subtract from balance
}


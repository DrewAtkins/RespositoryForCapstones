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
    final BigDecimal DOLLAR = new BigDecimal(1.00);
    final BigDecimal FIVE = new BigDecimal(5.00);
   final BigDecimal TEN = new BigDecimal(10.00);
   final BigDecimal TWENTY = new BigDecimal(20.00);

    public VendingMachine() {
        loadingMachineItems();
    }


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
            throw new IllegalArgumentException("Item doesn't fit into classes");
        }

    }



    public BigDecimal feedMoney(BigDecimal moneyAdded) {
        if (moneyAdded == TWENTY) {
            balance.add(TWENTY);
        } else if (moneyAdded == TEN) {
            balance.add(TEN);
        } else if (moneyAdded == FIVE) {
            balance.add(FIVE);
        } else if (moneyAdded == DOLLAR) {
            balance.add(DOLLAR);
        } return balance;
    }

        public BigDecimal getBalance() {
            return balance;
        }

        public BigDecimal returnChange() {
            return change;
        }

    }


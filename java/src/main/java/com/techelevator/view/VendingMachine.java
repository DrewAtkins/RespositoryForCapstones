package com.techelevator.view;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.text.SimpleDateFormat;


public class VendingMachine {

    private List<Product> vendingMachineItems = new ArrayList<Product>();
    BigDecimal balance = new BigDecimal(0);
    //BigDecimal balanceAfterChangeDispersed = new BigDecimal(0);

//


    //bills
    public final BigDecimal DOLLAR = new BigDecimal(1.00);
    public final BigDecimal FIVE = new BigDecimal(5.00);
    public final BigDecimal TEN = new BigDecimal(10.00);
    public final BigDecimal TWENTY = new BigDecimal(20.00);
    public BigDecimal balanceInCoins = new BigDecimal(0);
    public BigDecimal sadHundred = new BigDecimal(100.00);

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
    public void feedMoney(BigDecimal moneyAdded) {
        if (moneyAdded.equals(TWENTY)) {
            balance = balance.add(moneyAdded);
            log("Feed Money", TWENTY, balance);
        } else if (moneyAdded.equals(TEN)) {
            balance = balance.add(moneyAdded);
            log("Feed Money", TEN, balance);
        } else if (moneyAdded.equals(FIVE)) {
            balance = balance.add(moneyAdded);
            log("Feed Money", FIVE, balance);
        } else if (moneyAdded.equals(DOLLAR)) {
            balance = balance.add(moneyAdded);
            log("Feed Money", DOLLAR, balance);
        }
        System.out.println("Your balance is: " + balance.setScale(2));

    }

    public BigDecimal getBalance() {
        System.out.println(balance);
        return balance;
    }

    public void returnChange(BigDecimal balance) {

        balanceInCoins = (balance.multiply(sadHundred)); //changing balance to pennies
        int totalPennies = balanceInCoins.intValue();
        int quartersInPennies = totalPennies / 25;
        totalPennies -= quartersInPennies * 25;

        int dimesInPennies = totalPennies / 10;
        totalPennies -= dimesInPennies * 10;

        int nickelsInPennies = totalPennies / 5;
        totalPennies -= nickelsInPennies * 5;

        //needs work - returns quarters
        System.out.println("Your change is " + quartersInPennies + " in quarters, " + dimesInPennies + " in dimes, and " + nickelsInPennies + " in nickels.");

        log("Change Returned", balance, BigDecimal.ZERO);
        System.exit(0);

    }

    public BigDecimal subtractBalance(BigDecimal itemCost) {
        balance = balance.subtract(itemCost);
        return balance;
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
        String formattedInput = slotLocation.toUpperCase();

        Product foundProduct = null;
        for (Product product : products) {
            if (product.getSlotNumber().equals(formattedInput)) {
                foundProduct = product;
            }

        }
        if (foundProduct == null) {
            System.out.println("Invalid Selection!");
            return "Invalid Selection!";
        }


        if ((foundProduct.getInventoryCount() > 0) && (foundProduct.getCost().subtract(balance).compareTo(BigDecimal.ZERO) <= 0)){

        //if (foundProduct.getInventoryCount() > 0 && balance.compareTo(foundProduct.getCost()) >= 0) {
            subtractBalance(foundProduct.getCost());
            foundProduct.decrementInv();
            System.out.println(foundProduct.getItemName() + "|" + foundProduct.getCost() + "|" + getBalance() + "|" + foundProduct.getResponse());
            log(foundProduct.getItemName(), balance, (balance.subtract(foundProduct.getCost())) );

        } else if ((foundProduct.getInventoryCount() > 0) && (foundProduct.getCost().subtract(balance).compareTo(BigDecimal.ZERO) > 0)) {
            System.out.println("Insufficient funds, please insert more money.");
        } else {
            System.out.println("Item is out of stock");
        }

        return "Successful purchase";
    }

    public static void log(String typeOfInput, BigDecimal before, BigDecimal after) {

        File Log = new File("Log.txt");
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        try (PrintWriter logger = new PrintWriter(new FileOutputStream(Log,true))) {

            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String beforeFormatted = formatter.format(before);
            String afterFormatted = formatter.format(after);

            String formattedDate = today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            String formattedTime = time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));

           logger.println(formattedDate + " " + formattedTime + " " + typeOfInput + ": | " + beforeFormatted + " | " + afterFormatted  );




        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }

    }

}


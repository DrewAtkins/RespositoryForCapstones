package com.techelevator;

import com.techelevator.view.Chip;
import com.techelevator.view.Menu;
import com.techelevator.view.Product;
import com.techelevator.view.VendingMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	//used to create constant strings
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";

	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] PURCHASE_MENU = {PURCHASE_MENU_FEED_MONEY,PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

	private VendingMachine vendingMachine;
	private Menu menu;


	public VendingMachineCLI(Menu menu, VendingMachine vendingMachine) {
		this.menu = menu;
		this.vendingMachine = vendingMachine;
	}

	public void displayPage() {
		List<Product> products = vendingMachine.getVendingMachineItems();
		for (Product item : products) {
			System.out.println(item.getSlotNumber() + "|" + item.getItemName()  +
					"|" + item.getCost()  + "|" + item.getInventoryCount());
		}

	}
	public void run() throws FileNotFoundException {
		//vendingMachine.loadingMachineItems();

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				displayPage();
				//read from vending machine.csv
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				menu.getChoiceFromOptions(PURCHASE_MENU);

				//(3) Finish Transaction
				//

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}



	public static void main (String[]args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachine vendingMachine = new VendingMachine();
		VendingMachineCLI cli = new VendingMachineCLI(menu, vendingMachine);
		cli.run();
	}
}

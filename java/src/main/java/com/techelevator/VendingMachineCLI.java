package com.techelevator;

import com.techelevator.view.Chip;
import com.techelevator.view.Menu;
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
	//private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
	private static final String[] PURCHASE_MENU = {"Feed Money", "Select Product", "Exit"};


	private VendingMachine vendingMachine;
	private Menu menu;


	public VendingMachineCLI(Menu menu, VendingMachine vendingMachine) {
		this.menu = menu;
		this.vendingMachine = vendingMachine;
	}

	public void run() throws FileNotFoundException {
		

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				//vendingMachine.buildProduct();
				vendingMachine.loadingMachineItems();
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

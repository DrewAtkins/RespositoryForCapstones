package com.techelevator;

import com.techelevator.view.Chip;
import com.techelevator.view.Menu;

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
	private static final String[] PURCHASE_MENU = {"Feed Money", "Select Product", "Exit"};
	private static final String[] TEST_MENU = {"Test"};
	private static String[] testItemThing // trying to test this to see if it's splitting correctly
	private static List<String> vendingMachineItems = new ArrayList<String>();


	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				showVendingMachineItems();
				//read from vending machine.csv
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				menu.getChoiceFromOptions(PURCHASE_MENU);
				menu.getChoiceFromOptions(TEST_MENU);
				//(3) Finish Transaction
				//

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}

	private void showVendingMachineItems() throws FileNotFoundException {
		File vendItems = new File("vendingmachine.csv");
		try (Scanner readingVendItems = new Scanner(vendItems)) {
			while (readingVendItems.hasNextLine()) {
				String line = readingVendItems.nextLine();
				vendingMachineItems.add(line);
			}
			for (String item : vendingMachineItems) {
				System.out.println(item);
			}

		}
	}

	/*public void callCorrectClass() {
		for (String item : vendingMachineItems) {
			String splitItemTest = item.split("| ");
			if (splitItemTest[3].equals("Chip")) {
				System.out.println(splitItemTest);
				testItemThing.add(splitItemTest)
			}


		}
	}

	Trying to split the csv file by the pipe, and print out that list to see if it displays the way we want it to,
	also String[]? List<Array> pls help


	 */
	public static void main (String[]args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

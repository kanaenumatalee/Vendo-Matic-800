package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
														MAIN_MENU_OPTION_PURCHASE,
														MAIN_MENU_OPTION_EXIT,
														MAIN_MENU_OPTION_SALES_REPORT};

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	Item item = new Item();


	public void run() throws FileNotFoundException {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				item.displayItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				Purchase purchase = new Purchase(menu);
				purchase.run();

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT));
				System.exit(0);  //0 = clean close
			} //else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {

		//}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

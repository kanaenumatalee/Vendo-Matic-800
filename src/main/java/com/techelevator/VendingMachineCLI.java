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
	public VendingMachineCLI(Menu menu) {this.menu = menu;}
	Item item = new Item();
	Purchase purchase = new Purchase(item);

	public void run() throws FileNotFoundException {
		item.itemInfo();			     // [0-Slot, 1-Item, 2-Price,3-Type]
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				item.displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//Purchase purchase = new Purchase(menu, item);
				purchase.run();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you! Come again!");
				System.exit(0);  //0 = clean close
			} else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
				SalesReport sr = new SalesReport(item, purchase);
				sr.getSalesReport();
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

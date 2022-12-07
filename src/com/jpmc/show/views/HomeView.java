package com.jpmc.show.views;

import java.util.Scanner;

import com.jpmc.show.utils.ShowUtil;

public class HomeView implements View {

	private Scanner in;
	
	public HomeView(Scanner in) {
		this.in = in;
	}
	
	public void goToHome() {
		printViewHeader();
		
		while (in.hasNext()) {
			String currentInput = in.nextLine();
			
			ShowUtil.checkExitCommand(currentInput);
			
			if (validHomeInput(currentInput)) {
				// if Buyer, go to BuyerView
				if (currentInput.equals("1")) {
					BuyerView buyerView = new BuyerView(in);
					buyerView.goToBuyerView();
				} else if (currentInput.equals("2")) {
					// if Admin, go to Admin
					AdminView adminView = new AdminView(in);
					adminView.goToAdminView();
				}
				printViewHeader();
			}
		}
	}
	
	@Override
	public void printViewHeader() {
		System.out.println("----------------------------------");
		System.out.println();
		System.out.println("       WELCOME TO THE SHOWS       ");
		System.out.println();
		System.out.println("----------------------------------");

		System.out.println("** Input '0' anytime to return to previous screen");
		System.out.println("** Input '-1' anytime to exit the program");

		System.out.println();
		System.out.println("------------------------------");
		System.out.println("Use System as:");
		System.out.println("	[1] Buyer");
		System.out.println("	[2] Admin");
		System.out.print("Your input: ");
	}
	
	private boolean validHomeInput(String input) {
		return input.equals("1") || input.equals("2");
	}
}

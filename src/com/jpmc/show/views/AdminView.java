package com.jpmc.show.views;

import java.util.Scanner;

import com.jpmc.show.controllers.ShowController;
import com.jpmc.show.models.Ticket;
import com.jpmc.show.requests.SetupShowRequest;
import com.jpmc.show.requests.ViewShowRequest;
import com.jpmc.show.responses.ViewShowResponse;
import com.jpmc.show.utils.ShowUtil;

public class AdminView implements View {

	private Scanner in;
	private ShowController showController;
	
	public AdminView(Scanner in) {
		this.in = in;
		this.showController = ShowController.getInstance();
	}
	
	public void goToAdminView() {
		printViewHeader();
		
		while(in.hasNext()) {
			String currentInput = in.nextLine();
			
			ShowUtil.checkExitCommand(currentInput);
			
			// validate admin command
			if (true) {
				if (currentInput.equals("0")) {
					return;
				} else if (currentInput.toLowerCase().startsWith("setup")) {
					setupShow(currentInput);
				} else if (currentInput.toLowerCase().startsWith("view")) {
					viewShow(currentInput);
				}
				printViewHeader();
			}
		}
	}
	
	@Override
	public void printViewHeader() {
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("Hello admin.");
		System.out.print  ("	Your Command: ");
	}
	
	private void setupShow(String input) {
		try {
			SetupShowRequest setupShowRequest = buildSetupShowRequest(input);
			this.showController.setupShow(setupShowRequest);
			
			System.out.println("SUCCESS! Show " + setupShowRequest.getShowNumber() + " added.");
		} catch (Exception e) {
			
		}
	}
	
	private void viewShow(String input) {
		ViewShowRequest viewShowRequest = ShowUtil.buildViewShowRequest(input);
		
		ViewShowResponse viewShowResponse = this.showController.viewShow(viewShowRequest);
		
		if (viewShowResponse.getShowNumber() == 0) {
			System.out.println("No show exists for show number: " + viewShowRequest.getShowNum());
		} else {
			System.out.println("Viewing show: " + viewShowResponse.getShowNumber());
			for (Ticket ticket : viewShowResponse.getTickets()) {
				System.out.println("	Ticket: " + ticket.getTicketNumber() + ", buyer phone number: " + ticket.getBuyerPhoneNumber() + ", seats: " + ticket.seatsToString());
			}
		}
	}
	
	private SetupShowRequest buildSetupShowRequest(String input) {
		// Valid input: Setup <Show Number> <Number of Rows> <Number of seats per row> <Cancellation window in minutes>  
		String[] inputTokens = input.split(" ");
		int showNumber = Integer.valueOf(inputTokens[1]);
		int numOfRows = Integer.valueOf(inputTokens[2]);
		int numOfSeats = Integer.valueOf(inputTokens[3]);
		int cancellationMins = Integer.valueOf(inputTokens[4]);
		
		SetupShowRequest setupShowRequest = new SetupShowRequest();
		setupShowRequest.setShowNumber(showNumber);
		setupShowRequest.setNumOfRows(numOfRows);
		setupShowRequest.setNumOfSeats(numOfSeats);
		setupShowRequest.setCancellationMins(cancellationMins);
		
		return setupShowRequest;
	}
}

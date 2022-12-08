package com.jpmc.show.views;

import java.util.Scanner;

import com.jpmc.show.controllers.ShowController;
import com.jpmc.show.exceptions.BusinessException;
import com.jpmc.show.models.Ticket;
import com.jpmc.show.requests.SetupShowRequest;
import com.jpmc.show.requests.ViewShowRequest;
import com.jpmc.show.responses.ViewShowResponse;
import com.jpmc.show.utils.Constants;
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
			
			if (currentInput.equals("0")) {
				System.out.println();
				System.out.println();
				System.out.println();
				return;
			} else if (currentInput.toLowerCase().startsWith("setup")) {
				setupShow(currentInput);
			} else if (currentInput.toLowerCase().startsWith("view")) {
				viewShow(currentInput);
			} else {
				System.out.println(Constants.INVALID_INPUT_FORMAT_MSG);
			}
			printViewHeader();
		}
	}
	
	@Override
	public void printViewHeader() {
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("Hello admin.");
		System.out.print  ("-->> ");
	}
	
	private void setupShow(String input) {
		try {
			SetupShowRequest setupShowRequest = buildSetupShowRequest(input);
			this.showController.setupShow(setupShowRequest);
			
			System.out.println("SUCCESS! Show " + setupShowRequest.getShowNumber() + " added.");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void viewShow(String input) {
		try {
			ViewShowRequest viewShowRequest = ShowUtil.buildViewShowRequest(input);
			
			ViewShowResponse viewShowResponse = this.showController.viewShow(viewShowRequest);

			System.out.println("Viewing show: " + viewShowResponse.getShowNumber());
			
			if (!viewShowResponse.getTickets().isEmpty()) {
				for (Ticket ticket : viewShowResponse.getTickets()) {
					System.out.println("	Ticket: " + ticket.getTicketNumber() + ", buyer phone number: " + ticket.getBuyerPhoneNumber() + ", seats: " + ticket.seatsToString());
				}
			} else {
				System.out.println("No tickets sold for Show " + viewShowResponse.getShowNumber() + " yet");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private SetupShowRequest buildSetupShowRequest(String input) throws IllegalArgumentException, NumberFormatException {
		// Valid input: Setup <Show Number> <Number of Rows> <Number of seats per row> <Cancellation window in minutes>
		SetupShowRequest setupShowRequest = new SetupShowRequest();
		try {
			String[] inputTokens = input.split(" ");
			
			if (inputTokens.length != 5) { // expecting only 5 tokens
				throw new IllegalArgumentException(Constants.INVALID_INPUT_FORMAT_MSG);
			}
			int showNumber = Integer.valueOf(inputTokens[1]);
			int numOfRows = Integer.valueOf(inputTokens[2]);
			int numOfSeats = Integer.valueOf(inputTokens[3]);
			int cancellationMins = Integer.valueOf(inputTokens[4]);
			
			setupShowRequest.setShowNumber(showNumber);
			setupShowRequest.setNumOfRows(numOfRows);
			setupShowRequest.setNumOfSeats(numOfSeats);
			setupShowRequest.setCancellationMins(cancellationMins);
		} catch (NumberFormatException e) {
			// Catches decimal values
			throw new NumberFormatException("Error: Invalid input. Expecting number " + e.getMessage());
		} catch (IllegalArgumentException e) {
			throw e;
		}
		
		return setupShowRequest;
	}
}

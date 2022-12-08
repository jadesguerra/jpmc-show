package com.jpmc.show.views;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.jpmc.show.controllers.ShowController;
import com.jpmc.show.exceptions.BusinessException;
import com.jpmc.show.models.Seat;
import com.jpmc.show.models.Ticket;
import com.jpmc.show.requests.BookShowRequest;
import com.jpmc.show.requests.CancelTicketRequest;
import com.jpmc.show.requests.ViewShowRequest;
import com.jpmc.show.utils.ShowUtil;

public class BuyerView implements View {

	private Scanner in;
	private ShowController showController;
	
	public BuyerView(Scanner in) {
		this.in = in;
		this.showController = ShowController.getInstance();
	}
	
	public void goToBuyerView() {
		printViewHeader();
		
		while(in.hasNext()) {
			String currentInput = in.nextLine();
			
			ShowUtil.checkExitCommand(currentInput);
			
			//TODO: validate buyer command
			if (true) {
				if (currentInput.equals("0")) {
					return;
				} else if (currentInput.toLowerCase().startsWith("availability")) {
					viewAvailability(currentInput);
				} else if (currentInput.toLowerCase().startsWith("book")) {
					book(currentInput);
				} else if (currentInput.toLowerCase().startsWith("cancel")) {
					cancel(currentInput);
				} else {
					System.out.println("Error: Invalid input");
				}
				printViewHeader();
			}
		}
	}
	
	@Override
	public void printViewHeader() {
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("Hello buyer.");
		System.out.print  ("	Your Command: ");
	}

	private void cancel(String input) {
		try {
			CancelTicketRequest cancelTicketRequest = buildCancelTicketRequest(input);
			
			this.showController.cancelTicket(cancelTicketRequest);
			
			System.out.println("SUCCESS! Cancelled Ticket " + cancelTicketRequest.getTicketNumber());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// TODO: test cases
	// 	1. no availability
	//	2. no show number
	//	3. validate input
	private void book(String input) {
		try {
			BookShowRequest bookShowRequest = buildBookShowRequest(input);
			Ticket ticket = this.showController.bookShow(bookShowRequest);

			System.out.println("SUCCESS! Booked for Show " + ticket.getShowNumber() + ", Ticket " + ticket.getTicketNumber());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// TODO: test cases
	// 	1. no availability
	//	2. no show number
	//	3. validate input
	private void viewAvailability(String input) {
		try {
			ViewShowRequest viewShowRequest = ShowUtil.buildViewShowRequest(input);
			
			Map<String, Seat> availableSeats = showController.viewAvailibility(viewShowRequest);
			
			if (availableSeats.isEmpty()) {
				System.out.println("There are no available seats for Show " + viewShowRequest.getShowNum());
			} else {
				StringBuilder sb = new StringBuilder();
				int seatCounter = 0; // for knowing when to do next line
				for (Map.Entry<String, Seat> entry : availableSeats.entrySet()) {
					if (seatCounter % 10 == 0) {
						sb.append("\n");
					}
					sb.append(entry.getValue().getSeatId() + ", ");
					seatCounter++;
				}
				System.out.println(sb.toString());
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private CancelTicketRequest buildCancelTicketRequest(String input) {
		// Cancel  <Ticket#>  <Phone#>
		CancelTicketRequest cancelTicketRequest = new CancelTicketRequest();
		try {
			String[] inputTokens = input.split(" ");
			if (inputTokens.length != 3) { // expecting only 3 tokens
				throw new IllegalArgumentException("Error: Invalid input. Try this format: Cancel  <Ticket#>  <Phone#>");
			}
			int ticketNumber = Integer.valueOf(inputTokens[1]);
			int phoneNumber = Integer.valueOf(inputTokens[2]);
			
			cancelTicketRequest.setTicketNumber(ticketNumber);
			cancelTicketRequest.setPhoneNumber(phoneNumber);
		} catch (NumberFormatException e) {
			// Catches decimal values
			throw new NumberFormatException("Error: Invalid input. Expecting number " + e.getMessage());
		} catch (IllegalArgumentException e) {
			throw e;
		}
		
		return cancelTicketRequest;
	}

	private BookShowRequest buildBookShowRequest(String input) {
		// Book <Show Number> <Phone#> <Comma separated list of seats> 
		BookShowRequest bookShowRequest = new BookShowRequest();
		try {
			String[] inputTokens = input.split(" ");
			if (inputTokens.length != 4) { // expecting only 4 tokens
				throw new IllegalArgumentException("Error: Invalid input. Try this format: Book <Show Number> <Phone#> <Comma separated list of seats>");
			}
			int showNumber = Integer.valueOf(inputTokens[1]);
			int phoneNumber = Integer.valueOf(inputTokens[2]);
			String seats = inputTokens[3];
			
			List<String> seatsList = Arrays.asList(seats.split(","));
			
			bookShowRequest.setShowNumber(showNumber);
			bookShowRequest.setPhoneNumber(phoneNumber);
			bookShowRequest.setSeats(seatsList);
		} catch (NumberFormatException e) {
			// Catches decimal values
			throw new NumberFormatException("Error: Invalid input. Expecting number " + e.getMessage());
		} catch (IllegalArgumentException e) {
			throw e;
		}
		
		return bookShowRequest;
	}
}

package com.jpmc.show.controllers;

import java.util.List;
import java.util.Map;

import com.jpmc.show.exceptions.BusinessException;
import com.jpmc.show.models.Seat;
import com.jpmc.show.models.Show;
import com.jpmc.show.models.Ticket;
import com.jpmc.show.requests.BookShowRequest;
import com.jpmc.show.requests.CancelTicketRequest;
import com.jpmc.show.requests.SetupShowRequest;
import com.jpmc.show.requests.ViewShowRequest;
import com.jpmc.show.responses.ViewShowResponse;
import com.jpmc.show.services.ShowService;
import com.jpmc.show.utils.ShowBuilder;

public class ShowController {

	private static final ShowController instance = new ShowController();
	private ShowService showService;
	
	private ShowController() {
		this.showService = ShowService.getInstance();
	}
	
	public static ShowController getInstance() {
		return instance;
	}
	
	/* TODO: Validations:
	*	1. new show
	*	2. show number already exists
	*	3. show number greater than int max
	*	4. show number negative
	*	5. num of rows < 1
	*	6. num of rows > 26
	*	7. num of seats < 1
	*	8. num of seats > 10
	*	9. NaN
	*	10. doubles
	*/
	public void setupShow(SetupShowRequest setupShowRequest) throws BusinessException {
		ShowBuilder showBuilder = new ShowBuilder();
		showBuilder.setShowNumber(setupShowRequest.getShowNumber());
		showBuilder.setNumOfRows(setupShowRequest.getNumOfRows());
		showBuilder.setNumOfSeats(setupShowRequest.getNumOfSeats());
		showBuilder.setCancellationMins(setupShowRequest.getCancellationMins());
		
		Show show = showBuilder.build();
		this.showService.setupShow(show);
	}
	
	public ViewShowResponse viewShow(ViewShowRequest viewShowRequest) throws BusinessException {
		Show show = this.showService.viewShow(viewShowRequest.getShowNum());
		if (show == null) {
			throw new BusinessException("Error: Show " + viewShowRequest.getShowNum() + " does not exist.");
		}
		List<Ticket> tickets = this.showService.getTicketsForShow(show.getShowNumber());
		
		ViewShowResponse viewShowResponse = new ViewShowResponse();
		viewShowResponse.setShowNumber(show.getShowNumber());
		viewShowResponse.setTickets(tickets);
		
		return viewShowResponse;
	}
	
	public Map<String, Seat> viewAvailibility(ViewShowRequest viewShowRequest) throws BusinessException {
		Map<String, Seat> availableSeats = this.showService.viewAvailability(viewShowRequest.getShowNum());
		return availableSeats;
	}
	
	public Ticket bookShow(BookShowRequest bookShowRequest) throws BusinessException {
		Ticket ticket = this.showService.bookShow(bookShowRequest.getShowNumber(), bookShowRequest.getPhoneNumber(), bookShowRequest.getSeats());
		
		return ticket;
	}
	
	// TODO: validations
	// 	1. no ticket number
	//	2. phone number does not match?
	public void cancelTicket(CancelTicketRequest cancelTicketRequest) throws BusinessException {
		try {
			this.showService.cancelTicket(cancelTicketRequest.getTicketNumber());
		} catch (BusinessException e) {
			throw e;
		}
	}
}

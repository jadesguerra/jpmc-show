package com.jpmc.show.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpmc.show.models.Seat;
import com.jpmc.show.models.Show;
import com.jpmc.show.models.Ticket;
import com.jpmc.show.repositories.ShowRepository;
import com.jpmc.show.repositories.TicketRepository;

public class ShowService {

	private static final ShowService instance = new ShowService();
	private ShowRepository showRepository;
	private TicketRepository ticketRepository;
	
	private ShowService() {
		this.showRepository = ShowRepository.getInstance();
		this.ticketRepository = TicketRepository.getInstance();
	}
	
	public static ShowService getInstance() {
		return instance;
	}
	
	public void setupShow(Show show) {
		this.showRepository.add(show);
	}
	
	public Show viewShow(int id) {
		Show show = this.showRepository.getById(id);
		
		return show;
	}
	
	public Map<String, Seat> viewAvailability(int showNumber) {
		Show show = viewShow(showNumber);
		Map<String, Seat> seats = show.getSeats();
		Map<String, Seat> availableSeats = seats.entrySet()
			.stream()
			.filter(entry -> !entry.getValue().isReserved())
			.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		
		return availableSeats;
	}
	
	public Ticket bookShow(int showNumber, int phoneNumber, List<String> seats) {
		// update seats for show
		Show show = viewShow(showNumber);
		Map<String, Seat> showSeats = show.getSeats();
		
		for(String seatId : seats) {
			// TODO: handle if seats are already taken. Maybe store original seats as fallback?
			showSeats.get(seatId).setReserved(true);
		}
		this.showRepository.add(show);
		
		// create ticket
		Ticket ticket = new Ticket();
		ticket.setShowNumber(showNumber);
		ticket.setBuyerPhoneNumber(phoneNumber);
		ticket.setSeats(seats);
		ticket.setActive(true);
		
		// add ticket to ticketRepo
		this.ticketRepository.add(ticket);
		
		// TODO: is this still needed?
		// create buyer
		// add buyer to buyerRepo
		
		return ticket;
	}
	
	// TODO: check dates
	public void cancelTicket(int ticketNumber) {
		// retrieve ticket
		Ticket ticket = this.ticketRepository.getById(ticketNumber);
		ticket.setActive(false);
		int showNumber = ticket.getShowNumber();
		List<String> seats = ticket.getSeats();
		
		// go through seats and unreserve them
		Show show = this.showRepository.getById(showNumber);
		Map<String, Seat> showSeats = show.getSeats();
		for (String seat : seats) {
			showSeats.get(seat).setReserved(false);
		}
		show.setSeats(showSeats);
		this.showRepository.add(show);
		
		// don't update ticketRepo
	}
	
	public List<Ticket> getTicketsForShow(int showNumber) {
		return this.ticketRepository.getActiveTicketsForShow(showNumber);
	}
}

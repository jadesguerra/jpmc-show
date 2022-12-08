package com.jpmc.show.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.jpmc.show.models.Ticket;

public class TicketRepository implements Repository<Ticket> {

	private final static TicketRepository instance = new TicketRepository();
	private Map<Integer, Ticket> tickets;
	
	private TicketRepository() {
		this.tickets = new HashMap<Integer, Ticket>();
	}
	
	public static TicketRepository getInstance() {
		return instance;
	}
	
	@Override
	public void add(Ticket ticket) {
		int ticketNumber = generateTicketNumber();
		ticket.setTicketNumber(ticketNumber);
		
		tickets.put(ticketNumber, ticket);
	}

	@Override
	public Ticket getById(int ticketNumber) {
		return tickets.get(ticketNumber);
	}
	
	public List<Ticket> getActiveTicketsForShow(int showNumber) {
		List<Ticket> activeTicketsForShow = tickets.entrySet()
				.stream()
				.filter(entry -> entry.getValue().getShowNumber() == showNumber)
				.filter(entry -> entry.getValue().isActive())
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());
		return activeTicketsForShow;
	}
	
	public List<Ticket> getActiveTicketsForShowByPhoneNumber(int showNumber, int phoneNumber) {
		List<Ticket> activeTicketsForShow = this.getActiveTicketsForShow(showNumber);
		List<Ticket> ticketsByPhoneNumber = activeTicketsForShow
				.stream()
				.filter(ticket -> ticket.getBuyerPhoneNumber() == phoneNumber)
				.collect(Collectors.toList());
		return ticketsByPhoneNumber;
	}

	public Map<Integer, Ticket> getAll() {
		return tickets;
	}
	
	public int generateTicketNumber() {
		Random random = new Random();
		int upperBound = 1000000;
		int randomTicketId = random.nextInt(upperBound);
		
		while (getById(randomTicketId) != null) {
			randomTicketId = random.nextInt(upperBound);
		}
		
		return randomTicketId;
	}
}

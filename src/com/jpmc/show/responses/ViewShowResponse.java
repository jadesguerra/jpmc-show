package com.jpmc.show.responses;

import java.util.List;

import com.jpmc.show.models.Ticket;

public class ViewShowResponse {

	private int showNumber;
	private List<Ticket> tickets;
	
	public int getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
}

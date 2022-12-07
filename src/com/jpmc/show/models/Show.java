package com.jpmc.show.models;

import java.util.Map;

public class Show {

	private int showNumber;
	private int cancellationWindowMins;
	private Map<String, Seat> seats;
	
	public int getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
	}
	public int getCancellationWindowMins() {
		return cancellationWindowMins;
	}
	public void setCancellationWindowMins(int cancellationWindowMins) {
		this.cancellationWindowMins = cancellationWindowMins;
	}
	public Map<String, Seat> getSeats() {
		return seats;
	}
	public void setSeats(Map<String, Seat> seats) {
		this.seats = seats;
	}
	
	@Override
	public String toString() {
		// display Show Number, Ticket#, Buyer Phone#, Seat Numbers allocated to the buyer
		return "Show Number: " + showNumber;
	}
}

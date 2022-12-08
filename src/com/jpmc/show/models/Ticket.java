package com.jpmc.show.models;

import java.time.LocalDateTime;
import java.util.List;

public class Ticket {

	private int ticketNumber;
	private int showNumber;
	private int buyerPhoneNumber;
	private List<String> seats;
	private boolean isActive;
	private LocalDateTime cancellationWindow;
	
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public int getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
	}
	public int getBuyerPhoneNumber() {
		return buyerPhoneNumber;
	}
	public void setBuyerPhoneNumber(int buyerPhoneNumber) {
		this.buyerPhoneNumber = buyerPhoneNumber;
	}
	public List<String> getSeats() {
		return seats;
	}
	public void setSeats(List<String> seats) {
		this.seats = seats;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public LocalDateTime getCancellationWindow() {
		return cancellationWindow;
	}
	public void setCancellationWindow(LocalDateTime cancellationWindow) {
		this.cancellationWindow = cancellationWindow;
	}
	
	public String seatsToString() {
		StringBuilder sb = new StringBuilder();
		
		for (String seat : this.seats) {
			sb.append(seat);
			sb.append(" ");
		}
		
		return sb.toString();
	}
}

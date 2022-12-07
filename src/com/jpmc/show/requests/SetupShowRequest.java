package com.jpmc.show.requests;

public class SetupShowRequest {

	private int showNumber; //TODO: handle 001 vs 1
	private int numOfRows;
	private int numOfSeats;
	private int cancellationMins;
	
	public int getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
	}
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	public int getNumOfSeats() {
		return numOfSeats;
	}
	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	public int getCancellationMins() {
		return cancellationMins;
	}
	public void setCancellationMins(int cancellationMins) {
		this.cancellationMins = cancellationMins;
	}
}

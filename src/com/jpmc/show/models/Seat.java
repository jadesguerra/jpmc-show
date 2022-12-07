package com.jpmc.show.models;

public class Seat {

	private final String row; // A, B, C, etc
	private final int col;
	private final String seatId; // derived
	private boolean isReserved;
	
	public Seat(String row, int col) {
		this.row = row;
		this.col = col;
		this.seatId = row + col;
		this.setReserved(false);
	}
	
	public String getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}	
	
	public String getSeatId() {
		return seatId;
	}

	public boolean isReserved() {
		return isReserved;
	}

	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
}

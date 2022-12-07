package com.jpmc.show.utils;

import java.util.HashMap;
import java.util.Map;

import com.jpmc.show.models.Seat;
import com.jpmc.show.models.Show;

public class ShowBuilder {

	private int showNumber;
	private int numOfRows;
	private int numOfSeats;
	private int cancellationMins;
	
	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	public void setCancellationMins(int cancellationMins) {
		this.cancellationMins = cancellationMins;
	}
	
	public Show build() {
		// TODO: validate if properties are present
		Show show = new Show();
		show.setShowNumber(this.showNumber);
		show.setCancellationWindowMins(this.cancellationMins);
		show.setSeats(createSeats());
		
		return show;
	}
	
	private Map<String, Seat> createSeats() {
		Map<String, Seat> seats = new HashMap<String, Seat>();
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 1; j <= numOfSeats; j++) {
				char row = 'A';
				row += i;
				Seat newSeat = new Seat(String.valueOf(row), j);
				seats.put(newSeat.getSeatId(), newSeat);
			}
		}
		return seats;
	}
}

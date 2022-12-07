package com.jpmc.show.repositories;

import java.util.HashMap;
import java.util.Map;

import com.jpmc.show.models.Show;

public class ShowRepository implements Repository<Show> {

	private final static ShowRepository instance = new ShowRepository();
	private Map<Integer, Show> shows;
	
	private ShowRepository() {
		this.shows = new HashMap<Integer, Show>();
	}
	
	public static ShowRepository getInstance() {
		return instance;
	}

	@Override
	public void add(Show show) {
		shows.put(show.getShowNumber(), show);
	}

	@Override
	public Show getById(int id) {
		return shows.get(id);
	}

	public Map<Integer, Show> getAll() {
		return shows;
	}
}

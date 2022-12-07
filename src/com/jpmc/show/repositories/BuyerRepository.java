package com.jpmc.show.repositories;

import java.util.HashMap;
import java.util.Map;

import com.jpmc.show.models.Buyer;

public class BuyerRepository implements Repository<Buyer> {

	private final static BuyerRepository instance = new BuyerRepository();
	private Map<String, Buyer> buyers;
	
	private BuyerRepository() {
		this.buyers = new HashMap<String, Buyer>();
	}
	
	public static BuyerRepository getInstance() {
		return instance;
	}
	
	@Override
	public void add(Buyer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Buyer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Buyer> getAll() {
		return buyers;
	}
}

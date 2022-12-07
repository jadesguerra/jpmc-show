package com.jpmc.show.repositories;

public interface Repository<T> {
	
	void add(T o);
	T getById(int id);
}

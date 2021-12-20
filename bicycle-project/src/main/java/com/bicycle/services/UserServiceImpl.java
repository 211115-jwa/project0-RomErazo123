package com.bicycle.services;

import java.util.Set;

import com.bicycle.beans.Bike;
import com.bicycle.data.BicycleDAO;
import com.bicycle.data.postgres.BicyclePostgres;

public class UserServiceImpl implements UserService{
	private BicycleDAO bikeDao = new BicyclePostgres();
	
	@Override	// does not return a list
	public Bike searchBikeById(int id) {
		 return bikeDao.getById(id);
	}
	
	@Override		 // returns a list
	public Set<Bike> viewAllBikes() {
		return bikeDao.getAll();
	}

	@Override		 // returns a list
	public Set<Bike> searchBikesByBrand(String brand) {
		Set<Bike> a = bikeDao.getByBrand(brand);
		return a;
	}

	@Override		 // returns a list
	public Set<Bike> searchBikesByModel(String model) {
		Set<Bike> a = bikeDao.getByModel(model);
		return a;
	}

	@Override		 // returns a list
	public Set<Bike> searchBikesByOwner(String owner) {
		Set<Bike> a = bikeDao.getByOwner(owner);
		return a;
	}

}

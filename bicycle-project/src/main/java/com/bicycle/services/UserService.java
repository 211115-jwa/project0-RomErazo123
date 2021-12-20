package com.bicycle.services;

import java.util.Set;
import com.bicycle.beans.*;

public interface UserService {
	// services represent business logic - actual user activities.
	// what can a user do?
	public Set<Bike> viewAllBikes();
	public Bike searchBikeById(int id);
	public Set<Bike> searchBikesByBrand(String brand);
	public Set<Bike> searchBikesByModel(String model);
	public Set<Bike> searchBikesByOwner(String owner);
}

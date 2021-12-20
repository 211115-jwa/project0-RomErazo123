package com.bicycle.data;

import java.util.Set;

import com.bicycle.beans.Bike;

//DAO: data access object
//an object that is designed for exclusively accessing data (e.g. in a database)
//Bike the type

public interface BicycleDAO {
		
		// accessing the database should use CRUD operations:
		// create, read, update, delete
		public int create(Bike dataToAdd);
		public Bike getById(int id);
		public Set<Bike> getByBrand(String brand);
		public Set<Bike> getByModel(String model);
		public Set<Bike> getByOwner(String owner);
		public Set<Bike> getAll();
		public void update(Bike dataToUpdate);
		public void delete(Bike dataToDelete);

}

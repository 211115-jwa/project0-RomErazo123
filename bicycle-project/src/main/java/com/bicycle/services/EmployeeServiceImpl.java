package com.bicycle.services;
import com.bicycle.beans.*;
import com.bicycle.data.*;
import com.bicycle.data.postgres.*;
public class EmployeeServiceImpl implements EmployeeService {
	
	private BicycleDAO bicycleDao = new BicyclePostgres();
	
	@Override
	public int addNewBike(Bike newBike) {
		
		return bicycleDao.create(newBike);
	}

	@Override
	public Bike editBike(Bike bikeToEdit) {
		Bike bikeFromDatabase = bicycleDao.getById(bikeToEdit.getId());
		if (bikeFromDatabase != null) {
			bicycleDao.update(bikeToEdit);
			return bicycleDao.getById(bikeToEdit.getId());
		}
		return null;
	}

	@Override
	public Bike getBikeById(int id) {
		
		return bicycleDao.getById(id);
	}

}

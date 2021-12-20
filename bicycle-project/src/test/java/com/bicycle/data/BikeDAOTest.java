package com.bicycle.data;

import org.junit.jupiter.api.Test;

import com.bicycle.beans.*;

import com.bicycle.data.postgres.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class BikeDAOTest {
	// is this enough
	private BicycleDAO bikeDao = new BicyclePostgres();
	
	@Test // Test 
	public void getByIdWhenIdExists() {
		// setup
		int idInput = 1;
		// call the method we're testing
		Bike idOutput = bikeDao.getById(idInput);
		// assert that it did what we expected
		assertEquals(1, idOutput.getId());
	}
	

	@Test
	public void getByIdWhenIdDoesNotExists() {
		int idInput = -1;
		Bike petOutput =bikeDao.getById(idInput);
		assertNull(petOutput);
	}
	

	@Test
	public void getAll() {
		Set<Bike> givenOutput = bikeDao.getAll();
		assertNotNull(givenOutput);
		// assertNotNull(bikeDao.getall();
	}
	
	@Test
	public void addNewBike() {
		Bike newBike = new Bike();
		System.out.println(newBike);
		
		int generatedId = bikeDao.create(newBike);
		
		assertNotEquals(0, generatedId);
		System.out.println(generatedId);
	}
	
}

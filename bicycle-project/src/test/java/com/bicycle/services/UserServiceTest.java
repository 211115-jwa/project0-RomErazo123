package com.bicycle.services;

import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bicycle.beans.*;
import com.bicycle.data.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	// tell Mockito which classes/interfaces that we'll be mocking
		@Mock
		private BicycleDAO bicycleDao;
		
		// tell Mockito to override the regular DAOs with our mock DAOs
		@InjectMocks
		private UserService userServ = new UserServiceImpl();
		
		private static Set<Bike> mockBikes;
		
		@BeforeAll // 
		public static void mockBrandSetup() {
			mockBikes = new HashSet<>();
			
			for (int i=1; i<=5; i++) {
				Bike bike = new Bike();
				bike.setId(i);
				if (i<3)
					bike.setBrand("Yamaha");
				mockBikes.add(bike);
			}
		}// End of Setting up the Mock
		
		
		@Test // 213
		public void viewBikes() {
			when(bicycleDao.getAll()).thenReturn(mockBikes);
			
			Set<Bike> actualPets = userServ.viewAllBikes();
			
			assertEquals(mockBikes, actualPets);
		}
		
		@Test // 116
		public void searchByBrandExists() {
			String brand = "Yamaha";
			
			when(bicycleDao.getByBrand(brand)).thenReturn(mockBikes); 
			
			Set<Bike> actualBikes = userServ.searchBikesByBrand(brand);
			
			assertEquals(mockBikes, actualBikes);
			
		}
		
		@Test // 132
		public void searchByBrandDoesNotExist() {
			String brand = "qwertyuiop";
			
			when(bicycleDao.getByBrand(brand)).thenReturn(Collections.emptySet());
			
			Set<Bike> actualBike = userServ.searchBikesByBrand(brand);
			
			assertTrue(actualBike.isEmpty());
		}
}

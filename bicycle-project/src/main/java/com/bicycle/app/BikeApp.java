package com.bicycle.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.bicycle.services.*;

import com.bicycle.beans.*;

public class BikeApp {

	private static UserService userServ = new UserServiceImpl();
	private static EmployeeService emplyServ = new EmployeeServiceImpl();

	public static void main(String[] args) {

		Javalin app = Javalin.create();
		app.start();

		app.routes(() -> {
			// localhost:8080/bicycles			Postman
			path("/bicycles", () -> {
				get (ctx -> {	// Get to -> View all bicycle
					System.out.println("bicycle");
					Set<Bike> allBikes = userServ.viewAllBikes();
					ctx.json(allBikes);
				}); // End of get -> View all Bicycles

				post (ctx -> {	// Post to -> Add new bicycle
					// bodyAsClass turns JSON into a Java object based on the class you specify
					Bike newBike = ctx.bodyAsClass(Bike.class);

					if (newBike !=null) {
						emplyServ.addNewBike(newBike);
						ctx.status(HttpStatus.CREATED_201);
					} else {
						ctx.status(HttpStatus.BAD_REQUEST_400);
					}	
				}); // End of Post
			}); // End of path

			// localhost:8080/bicycles/brand
			path("/bicycles/brand", () -> {	
				get(ctx -> {			// Get to -> get by Brand
					// checking if they did /bicycle?brand=
					String brandSearch = ctx.queryParam("brand");
					// when using .equals with a String literal, put the
					// String literal first because it prevents null pointer
					// exceptions
					if (brandSearch != null && !"".equals(brandSearch)) {
						Set<Bike> bikesFound = userServ.searchBikesByBrand(brandSearch);
						ctx.json(bikesFound);
					} else {
						// if they didn't put the brand of the bike in the ?, you get all of them 
						Set<Bike> availableBike = userServ.viewAllBikes();
						ctx.json(availableBike);
					}
				}); // End of Get
			}); // End of Path

			// localhost:8080/bicycles/model
			path("/bicycles/model", () -> {
				get(ctx -> {			// Get to -> get by Model
					// checking if they did /bike?brand=
					String modelSearch = ctx.queryParam("model");
					// when using .equals with a String literal, put the
					// String literal first because it prevents null pointer
					// exceptions
					if (modelSearch != null && !"".equals(modelSearch)) {
						Set<Bike> modelFound = userServ.searchBikesByModel(modelSearch);
						ctx.json(modelFound);
					} else {
						// if they didn't put the brand of the bike in the ?, you get all of them 
						Set<Bike> availableBike = userServ.viewAllBikes();
						ctx.json(availableBike);
					}
				}); // End of Get
			}); // End of Path

			// localhost:8080/bicycles/8			Postman
			path("bicycles/{id}", () -> {
				get (ctx -> {		// Get to -> View bicycle by ID
					try {
						int bikeId = Integer.parseInt(ctx.pathParam("id")); // number format exception
						Bike bike = emplyServ.getBikeById(bikeId);	// getting the bikes id
						if (bike != null)
							ctx.json(bike);
						else
							ctx.status(404);
					}catch (NumberFormatException e) {
						ctx.status(400);
						ctx.result("The ID for the bicycle must be numeric");
					}
				}); // End of Get

				put(ctx -> {		// Put to -> Update Bicycle
					try {
						int bikeId = Integer.parseInt(ctx.pathParam("id")); // number format exception
						Bike bikeToEdit = ctx.bodyAsClass(Bike.class); 
						if (bikeToEdit != null && bikeToEdit.getId() == bikeId) {
							bikeToEdit = emplyServ.editBike(bikeToEdit);
							if (bikeToEdit != null)
								ctx.json(bikeToEdit);
							else
								ctx.status(404);
						} else {
							// conflict: the id doesn't match the id of the bike that was sent
							ctx.status(HttpCode.CONFLICT);
						}
					}catch (NumberFormatException e) {
						ctx.status(400);
						ctx.result("The ID for the bicycle must be numeric");
					}

				}); // End of Put

			}); // End of Path

		}); // End of Routes

	} // End of Main

} // End of Class

package com.bicycle.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.bicycle.utils.*;
import com.bicycle.data.*;
import com.bicycle.beans.*;


public class BicyclePostgres implements BicycleDAO{
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public int create(Bike dataToAdd) {
		int generatedId = 0;

		// try with resources
		try(Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);

			String sql = "insert into bicycles (id,brand,model,owner_name) " +
					"values (default, ?, ?, ?)";

			String[] key = {"id"};

			PreparedStatement pStmt = conn.prepareStatement(sql, key);

			// we need to set the values of the question marks
			//              ? index starts at 1
			pStmt.setString(1, dataToAdd.getBrand()); 
			pStmt.setString(2, dataToAdd.getModel());
			pStmt.setString(3, dataToAdd.getOwner());

			// after setting the values, we can run the statement
			pStmt.executeUpdate();
			ResultSet resultSet = pStmt.getGeneratedKeys();

			if (resultSet.next()) { // "next" goes to the next row in the result set (or the first row)
				// ID value from the result set
				generatedId = resultSet.getInt("id");
				conn.commit(); // TCL commit statement
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedId;
	}

	@Override
	public Bike getById(int id) {
		Bike bike = null;
		try (Connection conn = connUtil.getConnection()){
			String sql = "select * from bicycles where id=?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1,id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				bike = new Bike();
				bike.setId(id);
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setModel(resultSet.getString("owner_name"));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bike;
	}

	@Override
	public void update(Bike dataToUpdate) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "update bicycles set "
					+ "brand=?,model=?,owner_name=? "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dataToUpdate.getBrand());
			pStmt.setString(2, dataToUpdate.getModel());
			pStmt.setString(3, dataToUpdate.getOwner());
			pStmt.setInt(4, dataToUpdate.getId());

			int rowsAffected = pStmt.executeUpdate();

			if (rowsAffected==1) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Bike dataToDelete) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "delete from bicycles "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToDelete.getId());

			int rowsAffected = pStmt.executeUpdate();

			if (rowsAffected==1) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Set<Bike> getAll() {
		Set<Bike> allBikes = new HashSet<>();
		try(Connection conn = connUtil.getConnection()){
			String sql = "select * from bicycles";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				// create the Bike object to later use to fill the collection
				Bike bike = new Bike();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				bike.setId(resultSet.getInt("id"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setOwner(resultSet.getString("owner_name"));


				allBikes.add(bike);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allBikes;
	}

	@Override
	public Set<Bike> getByBrand(String brand) {

		Set<Bike> allBikes = new HashSet<>();
		try(Connection conn = connUtil.getConnection()){
			String sql = "select * from bicycles "+ "where brand=?";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				// create an object to later use to fill a collection
				Bike bike = new Bike();
				// pull the data from each row in the result set
				// and put it into the java object so that we can
				// add it to the collection later
				bike.setId(resultSet.getInt("id"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setOwner(resultSet.getString("owner_name"));

				// The collection is being filled
				allBikes.add(bike);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allBikes;
	}

	@Override
	public Set<Bike> getByModel(String model) {

		Set<Bike> allBikes = new HashSet<>();
		try(Connection conn = connUtil.getConnection()){
			String sql = "select * from bicycles "+ "where model=?";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				// create an object to later use to fill a collection
				Bike bike = new Bike();
				// pull the data from each row in the result set
				// and put it into the java object so that we can
				// add it to the collection later
				bike.setId(resultSet.getInt("id"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setOwner(resultSet.getString("owner_name"));

				// The collection is being filled
				allBikes.add(bike);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allBikes;
	}

	@Override
	public Set<Bike> getByOwner(String owner) {
		Set<Bike> allBikes = new HashSet<>();
		try(Connection conn = connUtil.getConnection()){
			String sql = "select * from bicycles "+ "where owner_name=?";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				// create an object to later use to fill a collection
				Bike bike = new Bike();
				// pull the data from each row in the result set
				// and put it into the java object so that we can
				// add it to the collection later
				bike.setId(resultSet.getInt("id"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setOwner(resultSet.getString("owner_name"));

				// The collection is being filled
				allBikes.add(bike);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allBikes;
	}

}

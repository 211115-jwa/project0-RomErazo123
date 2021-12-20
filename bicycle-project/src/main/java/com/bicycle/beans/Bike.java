package com.bicycle.beans;

public class Bike {

	private int id; // serial
	private String brand;
	private String model;
	private String owner;
	
	// Default Constructor
	public Bike() {
		id = 0;
		brand = "Brand";
		model = "Model";
		owner = "Romeo";
	}
	
	// Getters Read-Only
	public int getId() {
		return id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getOwner() {
		return owner;
	}
	
	// Setter Write-Only
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	// Special methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + id;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bike other = (Bike) obj;
		
		// brand
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (id != other.id)
			return false;
		
		// model
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		
		// owner
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return "Bike [id=" + id + ", brand =" + brand + ", model =" + model + ", owner_name =" + owner + "]";
	}
	
}

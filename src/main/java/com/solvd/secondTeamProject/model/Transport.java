package com.solvd.secondTeamProject.model;

public class Transport extends AbstractEntity {
	private String name;
	private double capacity;
	
	public Transport() {
		name="no-name";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
}

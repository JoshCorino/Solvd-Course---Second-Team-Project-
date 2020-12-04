package com.solvd.secondTeamProject.model;

public class Transport extends AbstractEntity {
	private String name;
	private Double capacity;
	
	public Double getCapacity() {
		return capacity;
	}
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	public Transport() {
		name="no-name";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Transport [name=" + name + " " + capacity + "]";
	}
	
}

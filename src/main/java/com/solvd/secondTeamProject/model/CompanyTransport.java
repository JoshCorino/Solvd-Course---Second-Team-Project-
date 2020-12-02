package com.solvd.secondTeamProject.model;

public class CompanyTransport extends AbstractEntity{
	private Transport transportType;
	private double capacity;
	
	public CompanyTransport() {}
	
	public Transport getTransportType() {
		return transportType;
	}
	public void setTransportType(Transport transportType) {
		this.transportType = transportType;
	}
	
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	
	
}

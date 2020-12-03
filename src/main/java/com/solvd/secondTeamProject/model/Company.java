package com.solvd.secondTeamProject.model;

import java.util.ArrayList;
import java.util.List;

public class Company extends AbstractEntity{
	
	private String name;
	private List<Order> orders;
	private List<CompanyTransport> transports;

	public Company() {
		name="no-name";
		transports = new ArrayList<CompanyTransport>();
		orders = new ArrayList<Order>();
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public void setTransports(List<CompanyTransport> transports) {
		this.transports = transports;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addTransport(CompanyTransport t) {
		transports.add(t);
	}
	
	public List<CompanyTransport> getTransports(){
		return transports;
	}
}

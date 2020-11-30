package com.solvd.secondTeamProject.model;

import java.util.ArrayList;
import java.util.List;

public class Company extends AbstractEntity{
	
	private String name;
	private List<Transport> transports;

	public Company() {
		name="no-name";
		transports=new ArrayList<Transport>();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addTransport(Transport t) {
		transports.add(t);
	}
}

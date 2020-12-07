package com.solvd.secondTeamProject.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractEntity{
	private String name;
	private List<Transport> allowedTransports;
	private List<Product> goods;
	
	public Warehouse() {
		name = "no-name";
		allowedTransports = new ArrayList<Transport>();
		goods = new ArrayList<Product>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void addTransport(Transport t) {
		allowedTransports.add(t);
	}
	
	public void addProduct(Product p) {
		goods.add(p);
	}
	
	@Override
	public String toString() {
		return "Warehouse [name=" + name + ", allowedTransports=" + allowedTransports + ", goods=" + goods + ", id="
				+ id + "]";
	}
	
	public ArrayList<Transport> getTransports() {
		return new ArrayList<Transport> (allowedTransports);
	}
	
	public ArrayList<Product> getProducts() {
		return new ArrayList<Product>(goods);
	}
	public void setAllowedTransports(List<Transport> allowedTransports) {
		this.allowedTransports = allowedTransports;
	}
	public void setGoods(List<Product> goods) {
		this.goods = goods;
	}
	
	
	
}

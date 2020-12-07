package com.solvd.secondTeamProject.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order extends AbstractEntity{
	private Date date;
	private List<Product> goods;
	
	public Order() {
		date=new Date(0);
		goods=new ArrayList<Product>();
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void addProduct(Product p) {
		goods.add(p);
	}
	@Override
	public String toString() {
		return "Order [date=" + date + ", goods=" + goods + ", id=" + id + "]";
	}
	public List<Product> getGoods() {
		return goods;
	}
	public void setGoods(List<Product> goods) {
		this.goods = goods;
	}
	
}

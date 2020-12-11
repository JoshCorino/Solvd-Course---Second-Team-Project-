package com.solvd.secondTeamProject;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.solvd.secondTeamProject.algorithm.Algorithm;
import com.solvd.secondTeamProject.algorithm.ParserResult;
import com.solvd.secondTeamProject.algorithm.ResultRepresentation;

import com.solvd.secondTeamProject.dao.jdbc.CompanyDAO;
import com.solvd.secondTeamProject.dao.mybatis.*;
import com.solvd.secondTeamProject.dao.services.CompanyService;
import com.solvd.secondTeamProject.dao.services.OrderService;
import com.solvd.secondTeamProject.dao.services.WarehouseService;
import com.solvd.secondTeamProject.model.*;

public class App{
	
	
	public static void main( String[] args ){
    	Logger log = LogManager.getLogger(App.class);
    	
    	WarehouseService ws = new WarehouseService();
    	List<Warehouse> warehouses = ws.getAll();
    	
    	CompanyService cs = new CompanyService();
    	Company company = cs.getCompany(1);
    	
    	List<Product> goods1 = warehouses.get(0).getProducts();
    	List<Product> goods2 = warehouses.get(1).getProducts();
    	
    	Order o1 = new Order();
    	List<Product> goodOrder1 = new ArrayList<Product>();
    	goodOrder1.add(goods1.get(0));
    	goodOrder1.add(goods2.get(1));
		goods1.get(0).setQuantity(2l);
		goods2.get(1).setQuantity(5l);
    	o1.setGoods(goodOrder1);
		
    	Order o2 = new Order();
    	List<Product> goodOrder2 = new ArrayList<Product>();
    	goodOrder2.add(goods1.get(1));
		goods1.get(1).setQuantity(1l);
    	o2.setGoods(goodOrder2);
    	
    	List<Order> orders = new ArrayList<Order>();
    	orders.add(o1); orders.add(o2);
    	
    	/*
    	Transport car1 = new Transport();
    	car1.setName("car");
    	car1.setCapacity(10.0);	
    	Transport car2 = new Transport();
    	car2.setName("car");
    	car2.setCapacity(9.0);
    	Transport plane = new Transport();
    	plane.setName("plane");
    	plane.setCapacity(40.0);	
    	
    	List<Transport> allowedTransports1 = new ArrayList<Transport>();
		allowedTransports1.add(car1);
		List<Transport> allowedTransports2 = new ArrayList<Transport>();
		allowedTransports2.add(plane);
		
		Product p1 = new Product();
		p1.setName("sofa");
		p1.setVolume(20);
		Product p2 = new Product();
		p2.setName("chair");
		p2.setVolume(3);
		Product p3 = new Product();
		p3.setName("table");
		p3.setVolume(13);
		Product p4 = new Product();
		p4.setName("cake");
		p4.setVolume(6);
		Product p5 = new Product();
		p5.setName("bread");
		p5.setVolume(3);
		Product p6 = new Product();
		p6.setName("coockie");
		p6.setVolume(2);
		
		List<Product> goodsFurniture = new ArrayList<Product>();
		goodsFurniture.add(p1);goodsFurniture.add(p2);goodsFurniture.add(p3);
		List<Product> goodsFood = new ArrayList<Product>();
		goodsFood.add(p4);goodsFood.add(p5);goodsFood.add(p6);

		Warehouse wFurniture = new Warehouse();
		wFurniture.setName("Furniture");
		wFurniture.setAllowedTransports(allowedTransports1);	
		wFurniture.setGoods(goodsFurniture);
		Warehouse wFood = new Warehouse();
		wFood.setName("Food");
		wFood.setAllowedTransports(allowedTransports2);	
		wFood.setGoods(goodsFood);
		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		warehouses.add(wFurniture);warehouses.add(wFood);
		
		Order o1 = new Order();
    	List<Product> goodOrder1 = new ArrayList<Product>();
    	goodOrder1.add(p2);goodOrder1.add(p5);
		p2.setQuantity(2l);p5.setQuantity(5l);
    	o1.setGoods(goodOrder1);
		
    	Order o2 = new Order();
    	List<Product> goodOrder2 = new ArrayList<Product>();
    	goodOrder2.add(p3);
		p3.setQuantity(1l);
    	o2.setGoods(goodOrder2);
    	
    	List<Order> orders = new ArrayList<Order>();
    	orders.add(o1); orders.add(o2);
    	
    	Company company = new Company();
    	
    	List<Transport> companyTransports = new ArrayList<Transport>();
    	companyTransports.add(car1);companyTransports.add(car2);companyTransports.add(plane);
    	company.setTransports(companyTransports); 	   	
   	*/
    	List<ResultRepresentation> bestTransports = new ArrayList<ResultRepresentation>();
    	bestTransports = Algorithm.bestTransports(orders,company,warehouses);
    	
    	OrderService os = new OrderService();
    	os.save(o1, company);
    	os.save(o2, company);
    	
    	ParserResult.parserResult(bestTransports, "src/main/resources/bestTransports.json");

    }
   
}

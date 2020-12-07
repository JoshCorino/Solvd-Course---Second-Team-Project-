package com.solvd.secondTeamProject;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.solvd.secondTeamProject.algorithm.Algorithm;
import com.solvd.secondTeamProject.algorithm.ParserResult;
import com.solvd.secondTeamProject.algorithm.ResultRepresentation;

import com.solvd.secondTeamProject.dao.jdbc.CompanyDAO;
import com.solvd.secondTeamProject.dao.mybatis.*;

import com.solvd.secondTeamProject.model.*;

public class App{
	
	
	public static void main( String[] args ){
    	Logger log = LogManager.getLogger(App.class);

//    	Company company = new Company();
//    	company.setName("DHL");
//
//    	Order order = new Order();
//    	order.setDate(new Date(0));
//
//    	Product product = new Product();
//    	product.setName("Tv");
//    	product.setPrice(100);
//    	product.setVolume(10);
//    	product.setQuantity(2l);
//    	
//    	Transport transport = new Transport();
//    	transport.setName("train");
//    	transport.setCapacity(123d);
//    	
//    	Warehouse warehouse = new Warehouse();
//    	warehouse.setName("Warehouse");
//        	
//        CompanyDAO cDAO = new CompanyDAO();
//        OrderDAO oDAO = new OrderDAO();
//        ProductDAO pDAO = new ProductDAO();
//        TransportDAO tDAO = new TransportDAO();
//        WarehouseDAO wDAO = new WarehouseDAO();
//        
//        cDAO.save(company);
//        oDAO.save(order, company);
//        pDAO.save(product);
//        tDAO.save(transport);
//        wDAO.save(warehouse);
//        
//        WarehouseGoodsDAO wgDAO = new WarehouseGoodsDAO();
//        WarehouseTransportsDAO wtDAO = new WarehouseTransportsDAO();
//        CompanyTransportDAO ctDAO = new CompanyTransportDAO();
//        
//        wgDAO.relate(warehouse, product);
//        wtDAO.relate(warehouse, transport);
//        ctDAO.relate(company, transport);
//        
//        log.info(wgDAO.getGoodsByWarehouseId(1));
//        log.info(wtDAO.getTransportsByWarehouseId(1));
//        log.info(ctDAO.getTransportsByCompanyId(1));
//        
//        Company companyGetted = cDAO.getById(1);
//    	log.info(companyGetted.toString());
//
//    	Order orderGetted = oDAO.getById(1);
//    	log.info(orderGetted.toString());
//
//    	Product productGetted = pDAO.getById(1);
//    	log.info(productGetted.toString());
//
//    	
//    	Transport transportGetted = tDAO.getById(1);
//    	log.info(transportGetted.toString());
//    	
//    	Warehouse warehouseGetted = wDAO.getById(1);
//    	log.info(warehouseGetted.toString());
//    	
//        
//        oDAO.remove(1);
//        cDAO.remove(1);
//        pDAO.remove(1);
//        tDAO.remove(1);
//        wDAO.remove(1);
    	
    	Transport car1 = new Transport();
    	car1.setName("car");
    	car1.setCapacity(10.0);
    	car1.setId(1);
    	Transport car2 = new Transport();
    	car2.setName("car");
    	car2.setCapacity(9.0);
    	car2.setId(2);
    	Transport plane = new Transport();
    	plane.setName("plane");
    	plane.setCapacity(40.0);
    	plane.setId(3);
    	
    	List<Transport> allowedTransports1 = new ArrayList<Transport>();
		allowedTransports1.add(car1);
		List<Transport> allowedTransports2 = new ArrayList<Transport>();
		allowedTransports2.add(plane);
		
    	Product p1 = new Product();
		p1.setName("sofa");
		p1.setVolume(20);
		Product p2 = new Product();
		p2.setName("chair");
		p2.setVolume(9);
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
    	company.setId(1);
    	List<Transport> companyTransports = new ArrayList<Transport>();
    	companyTransports.add(car1);companyTransports.add(car2);companyTransports.add(plane);
    	company.setTransports(companyTransports);
    	
    	List<ResultRepresentation> bestTransports = new ArrayList<ResultRepresentation>();
    	bestTransports = Algorithm.bestTransports(orders,company,warehouses);
    	
    	ParserResult.parserResult(bestTransports, "C:/Users/USUARIO/Desktop/bestTransports.json");

    }
   
}

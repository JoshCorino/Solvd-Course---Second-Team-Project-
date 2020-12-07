package com.solvd.secondTeamProject;


import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.secondTeamProject.algorithm.ResultRepresentation;
import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.dao.ICompanyTransportDAO;
import com.solvd.secondTeamProject.dao.IOrderGoodsDAO;
import com.solvd.secondTeamProject.dao.IProductDAO;
import com.solvd.secondTeamProject.dao.ITransportDAO;
import com.solvd.secondTeamProject.dao.IWarehouseDAO;
import com.solvd.secondTeamProject.dao.IWarehouseGoodsDAO;
import com.solvd.secondTeamProject.dao.IWarehouseTransportsDAO;
import com.solvd.secondTeamProject.dao.jdbc.CompanyDAO;
import com.solvd.secondTeamProject.dao.mybatis.*;
import com.solvd.secondTeamProject.dao.services.CompanyTransportService;
import com.solvd.secondTeamProject.dao.services.OrderService;
import com.solvd.secondTeamProject.dao.services.WarehouseService;
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
    	
    	
    	OrderDAO oDAO = new OrderDAO();
    	//List<Order> orders = oDAO.getOrders();
    	
    	List<ResultRepresentation> bestTransports = new ArrayList<ResultRepresentation>();
    	//bestTransports = bestTransports(orders,1);
    	
    	//Json
    	ObjectMapper obj = new ObjectMapper();
    	try {
    		obj.writeValue(new File("BestTransports.json"), bestTransports);
		} catch (IOException e) {
			log.error(e);
		}
    	
    }
   
}

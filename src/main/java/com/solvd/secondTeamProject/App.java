package com.solvd.secondTeamProject;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.dao.ICompanyTransportDAO;
import com.solvd.secondTeamProject.dao.IOrderGoodsDAO;
import com.solvd.secondTeamProject.dao.ITransportDAO;
import com.solvd.secondTeamProject.dao.IWarehouseDAO;
import com.solvd.secondTeamProject.dao.IWarehouseGoodsDAO;
import com.solvd.secondTeamProject.dao.mybatis.*;
import com.solvd.secondTeamProject.model.*;

public class App{
    
	public List<Transport> bestTransports(Company c, List<Warehouse> warehouses){
		List<Transport> bestTransports = new ArrayList<Transport>();
		boolean flag = false;
		List<Order> orders = c.getOrders();
		for (Order o : orders) {
			List<Product> goods = o.getGoods();
			for (Product g : goods) {
				//que warehouse tiene los productos q necesito (orden)
				for (Warehouse w : warehouses) { 
					if (w.getProducts().contains(g)) {
						//Necesito saber si ese warehouse tiene el tranporte q necesito
						List<Transport> allowedTransports = w.getTransports();
						for (Transport tw : allowedTransports) {
							//Me fijo primero en la lista de los q ya use
							for (Transport tb : bestTransports) {
								//Si son del tipo q necesito
								if (tb.getName().equals(tw.getName())) {
									//controlo la capacidad
									if (tb.getCapacity().compareTo(g.getVolume()) >= 0) {
										tb.setCapacity(tb.getCapacity()-g.getVolume());
										flag = true;
										break;
									}
								}
							}
							//Si no use nada de bestTransports
							if (!flag) {
								List<Transport> companyTransports = c.getTransports();
								if (!bestTransports.isEmpty())
									companyTransports.removeAll(bestTransports);
								for(Transport tc : companyTransports) {
									if (tc.getName().equals(tw.getName())) {
										//Necesito controlar la capacidad de los transportes q voy usando
										if (tc.getCapacity().compareTo(g.getVolume()) >= 0) {
											bestTransports.add(tc);
											tc.setCapacity(tc.getCapacity()-g.getVolume());
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			
		}
		
		
		
	
		
		return bestTransports;
	}
	
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
//    	
//    	Transport transport = new Transport();
//    	transport.setName("train");
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
//        oDAO.save(order, 1);
//        pDAO.save(product);
//        tDAO.save(transport);
//        wDAO.save(warehouse);
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
        
        ICompanyDAO cd = new CompanyDAO();
        //System.out.println(cd.getAll());
        
        ITransportDAO td = new TransportDAO();
        //System.out.println(td.getById(1));
        
        ICompanyTransportDAO ctd = new CompanyTransportDAO();
        //System.out.println(ctd.getTransportsByCompanyId(1l));
        
        IOrderGoodsDAO ogd = new OrderGoodsDAO();
        //System.out.println(ogd.getProductsByOrderId(2));
        
        IWarehouseDAO whd = new WarehouseDAO();
        //System.out.println(whd.getWarehouseById(2l));
        
        IWarehouseGoodsDAO whgd = new WarehouseGoodsDAO();
        System.out.println(whgd.getGoodsByWarehouseId(2));
    }
   
}

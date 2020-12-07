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
	private Logger log = LogManager.getLogger(App.class);
	
	private static WarehouseDAO wDAO = new WarehouseDAO();
	private static WarehouseService wService = new WarehouseService();
	private static CompanyTransportService ctService = new CompanyTransportService();
	private static CompanyDAO cDAO = new CompanyDAO();
	private static OrderService oService = new OrderService();
	
	private static class ProductShipping{
		private Company c;
		private Product p;
		
		public String toString() {
			return "ProductShipping [company="+c+",product="+p;
		}
		
		//GETTERS
		public Company getC() {return c;}
		public Product getP() {return p;}
		//SETTERS
		public void setC(Company c) {this.c = c;}
		public void setP(Product p) {this.p = p;}
		
	}
	
	private static class ResultRepresentation{
		private Transport transport;
		private List<ProductShipping> productsShipped;
		
		public String toString() {
			return "ResultRepresentation [transport="+transport+", productsShipped="+productsShipped;
		}
		
		//GETTERS
		public List<ProductShipping> getProductsShipped() {return productsShipped;}
		public Transport getTransport() {return transport;}
		//SETTERS
		public void setProductsShipped(List<ProductShipping> productsShipped) {this.productsShipped = productsShipped;}
		public void setTransport(Transport transport) {this.transport = transport;}
	}
	
	
	
	public static List<ResultRepresentation> bestTransports(List<Order> orders, long companyId){
		List<ResultRepresentation> bestTransports = new ArrayList<ResultRepresentation>();
		boolean foundTransport = false;
		for (Order o : orders) {
			List<Product> goods = oService.getGoodsByOrderId(o.getId());
			for (Product g : goods) {
				foundTransport = false;
				//que warehouse tiene los productos q necesito (todas las warehouses cargadas)
				for (Warehouse w : wDAO.getAll()) { 
					if (wService.getGoodsByWarehouseId(w.getId()).contains(g)) {
						//Necesito saber si ese warehouse tiene el tranporte q necesito
						List<Transport> allowedTransports = wService.getTransportsAllowedByWarehouseId(w.getId());
						for (Transport tw : allowedTransports) {
							//Me fijo primero en la lista de los q ya use
							for (ResultRepresentation tb : bestTransports) {
								//Si son del tipo q necesito
								if (tb.getTransport().getName().equals(tw.getName())) {
									//controlo la capacidad
									if (tb.getTransport().getCapacity().compareTo(g.getVolume()*g.getQuantity()) >= 0) {
										tb.getTransport().setCapacity(tb.getTransport().getCapacity()-(g.getVolume()*g.getQuantity()));
										ProductShipping ps = new ProductShipping();
										ps.setC(cDAO.getCompanyById(companyId));
										ps.setP(g);
										tb.getProductsShipped().add(ps);
										foundTransport = true;
										break;
									}
								}
							}
							//Si no use nada de bestTransports
							if (!foundTransport) {
								List<Transport> companyTransports = ctService.getTransportsByCompanyId(companyId);
								//Para no agarrar algun transporte que ya use
								//if (!bestTransports.isEmpty())
								//	companyTransports.removeAll(bestTransports);
								for(Transport tc : companyTransports) {
									if (tc.getName().equals(tw.getName())) {
										//Necesito controlar la capacidad de los transportes q voy usando
										if (tc.getCapacity().compareTo(g.getVolume()*g.getQuantity()) >= 0) {
											tc.setCapacity(tc.getCapacity()-(g.getVolume()*g.getQuantity()));
											ResultRepresentation rr = new ResultRepresentation();
											ProductShipping ps = new ProductShipping();
											ps.setC(cDAO.getCompanyById(companyId));
											ps.setP(g);
											rr.getProductsShipped().add(ps);
											rr.setTransport(tc);
											bestTransports.add(rr);	
											foundTransport = true;
											break;
										}
									}
								}
							}
							if (foundTransport)
								break;
						}
					}
					if (foundTransport)
						break;
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
    	
    	
    	//--------------------------------------------
    	//json test
    	List<ProductShipping> lps1 = new ArrayList<ProductShipping>();
    	ProductShipping ps1 = new ProductShipping();
    	Company c1 = new Company();
    	c1.setId(1);
    	ps1.setC(c1);
    	Product p1 = new Product();
    	p1.setId(1);
    	p1.setName("oreo");
    	ps1.setP(p1);
    	lps1.add(ps1);
    	ProductShipping ps2 = new ProductShipping();
    	ps2.setC(c1);
    	Product p2 = new Product();
    	p2.setId(4);
    	p2.setName("silla");
    	ps2.setP(p2);
    	lps1.add(ps2);
    	ResultRepresentation rr1 = new ResultRepresentation();
    	rr1.setProductsShipped(lps1);
    	Transport t1 = new Transport();
    	t1.setId(1);
    	t1.setName("car1");
    	Transport t2 = new Transport();
    	t2.setId(1);
    	t2.setName("plane1");
    	rr1.setTransport(t2);
    	
    	bestTransports.add(rr1);
    	//----------------------------------------------
    	
    	//Json
    	ObjectMapper obj = new ObjectMapper();
    	String json = "";
    	try {
    		obj.writeValue(new File("BestTransports.json"), bestTransports);
		} catch (IOException e) {
			log.error(e);
		}
    	
    }
   
}

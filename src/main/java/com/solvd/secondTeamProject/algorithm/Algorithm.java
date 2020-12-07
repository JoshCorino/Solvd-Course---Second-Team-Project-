package com.solvd.secondTeamProject.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.App;
import com.solvd.secondTeamProject.dao.jdbc.OrderDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Transport;
import com.solvd.secondTeamProject.model.Warehouse;

public class Algorithm {
	private static Logger log = LogManager.getLogger(Algorithm.class);
	
	public static List<ResultRepresentation> bestTransports(List<Order> orders, Company company, List<Warehouse> warehouses){
		List<ResultRepresentation> bestTransports = new ArrayList<ResultRepresentation>();
		boolean foundTransport = false;
		for (Order o : orders) {
			List<Product> goods = o.getGoods();
			for (Product g : goods) {
				foundTransport = false;
				for (Warehouse w : warehouses) { 
					if (w.getProducts().contains(g)) {
						List<Transport> allowedTransports = w.getTransports();
						for (Transport tw : allowedTransports) {
							for (ResultRepresentation tb : bestTransports) {
								if (tb.getTransport().getName().equals(tw.getName())) {
									if (tb.getTransport().getCapacity().compareTo(g.getVolume()*g.getQuantity()) >= 0) {
										tb.getTransport().setCapacity(tb.getTransport().getCapacity()-(g.getVolume()*g.getQuantity()));										
										tb.addProduct(company,g);
										foundTransport = true;
										break;
									}
								}
							}
							if (!foundTransport) {
								List<Transport> companyTransports = company.getTransports();
								for(Transport tc : companyTransports) {
									if (tc.getName().equals(tw.getName())) {
										if (tc.getCapacity().compareTo(g.getVolume()*g.getQuantity()) >= 0) {
											tc.setCapacity(tc.getCapacity()-(g.getVolume()*g.getQuantity()));
											ResultRepresentation rr = new ResultRepresentation();
											rr.addProduct(company,g);
											rr.setTransport(tc);
											bestTransports.add(rr);	
											foundTransport = true;
											companyTransports.remove(tc);
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
				if (!foundTransport)
					log.info("There is not a transport available for this good: "+g.getName());
			}
			
		}
		company.addOrders(orders);
		return bestTransports;
	}
}

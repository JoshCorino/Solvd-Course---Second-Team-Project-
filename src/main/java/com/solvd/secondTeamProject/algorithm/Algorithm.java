package com.solvd.secondTeamProject.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.secondTeamProject.App;
import com.solvd.secondTeamProject.dao.jdbc.CompanyDAO;
import com.solvd.secondTeamProject.dao.mybatis.WarehouseDAO;
import com.solvd.secondTeamProject.dao.services.CompanyTransportService;
import com.solvd.secondTeamProject.dao.services.OrderService;
import com.solvd.secondTeamProject.dao.services.WarehouseService;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Transport;
import com.solvd.secondTeamProject.model.Warehouse;

public class Algorithm {
	private Logger log = LogManager.getLogger(App.class);
	
	private static WarehouseDAO wDAO = new WarehouseDAO();
	private static WarehouseService wService = new WarehouseService();
	private static CompanyTransportService ctService = new CompanyTransportService();
	private static CompanyDAO cDAO = new CompanyDAO();
	private static OrderService oService = new OrderService();
		
	public List<ResultRepresentation> bestTransports(List<Order> orders, long companyId){
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
										tb.addProduct(cDAO.getCompanyById(companyId),g);
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
											rr.addProduct(cDAO.getCompanyById(companyId),g);
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
}

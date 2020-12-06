package com.solvd.secondTeamProject.dao.services;

import com.solvd.secondTeamProject.dao.IProductDAO;
import com.solvd.secondTeamProject.dao.ITransportDAO;
import com.solvd.secondTeamProject.dao.IWarehouseDAO;
import com.solvd.secondTeamProject.dao.jdbc.ProductDAO;
import com.solvd.secondTeamProject.dao.jdbc.TransportDAO;
import com.solvd.secondTeamProject.dao.jdbc.WarehouseDAO;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Transport;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseService {
	IWarehouseDAO wDAO = new WarehouseDAO();
	ITransportDAO tDAO = new TransportDAO();
	IProductDAO pDAO = new ProductDAO();
	
	public Warehouse save(Warehouse w) {
		for(Transport t: w.getTransports()) {
			tDAO.save(t);
		}
		for(Product p: w.getProducts()) {
			pDAO.save(p);
		}
		return wDAO.save(w);
	}
}

package com.solvd.secondTeamProject.dao.services;


import java.util.List;

import com.solvd.secondTeamProject.dao.IProductDAO;
import com.solvd.secondTeamProject.dao.ITransportDAO;
import com.solvd.secondTeamProject.dao.IWarehouseDAO;
import com.solvd.secondTeamProject.dao.IWarehouseGoodsDAO;
import com.solvd.secondTeamProject.dao.IWarehouseTransportsDAO;
import com.solvd.secondTeamProject.dao.jdbc.ProductDAO;
import com.solvd.secondTeamProject.dao.jdbc.TransportDAO;
import com.solvd.secondTeamProject.dao.jdbc.WarehouseDAO;
import com.solvd.secondTeamProject.dao.jdbc.WarehouseGoodsDAO;
import com.solvd.secondTeamProject.dao.jdbc.WarehouseTransportsDAO;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Transport;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseService {
	private IWarehouseDAO wDAO = new WarehouseDAO();
	private ITransportDAO tDAO = new TransportDAO();
	private IProductDAO pDAO = new ProductDAO();
	private IWarehouseGoodsDAO wgDAO= new WarehouseGoodsDAO();
	private	IWarehouseTransportsDAO wtDAO= new WarehouseTransportsDAO();
	
	public Warehouse save(Warehouse w) {
		Warehouse result =wDAO.save(w);
		for(Transport t: result.getTransports()) {
			tDAO.save(t);
			wtDAO.relate(result, t);
		}
		for(Product p: result.getProducts()) {
			pDAO.save(p);
			wgDAO.relate(result, p);
		}
		return result;
	}
	
	public Warehouse getWarehouse(long id){
		Warehouse result = wDAO.getWarehouseById(id);
		result.setAllowedTransports(wtDAO.getTransportsByWarehouseId(id));
		result.setGoods(wgDAO.getGoodsByWarehouseId(id));
		return result;
	}
	
	public List<Warehouse> getAll(){
		List<Warehouse> warehouses = wDAO.getAll();
		for (Warehouse w : warehouses) {
			w.setAllowedTransports(wtDAO.getTransportsByWarehouseId(w.getId()));
			w.setGoods(wgDAO.getGoodsByWarehouseId(w.getId()));
		}
		return warehouses;
	}
}

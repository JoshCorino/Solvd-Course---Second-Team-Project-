package com.solvd.secondTeamProject.dao.services;

import com.solvd.secondTeamProject.dao.IOrderDAO;
import com.solvd.secondTeamProject.dao.IProductDAO;
import com.solvd.secondTeamProject.dao.jdbc.OrderDAO;
import com.solvd.secondTeamProject.dao.jdbc.ProductDAO;
import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Product;

public class OrderService {
	IOrderDAO oDAO = new OrderDAO();
	IProductDAO pDAO = new ProductDAO();
	
	public Order save(Order c, long companyId) {
		for(Product p : c.getGoods()) {
			pDAO.save(p);
		}
		return oDAO.save(c,companyId);
	}
}

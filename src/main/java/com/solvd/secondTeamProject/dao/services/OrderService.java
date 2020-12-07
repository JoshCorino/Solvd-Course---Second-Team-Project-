package com.solvd.secondTeamProject.dao.services;


import com.solvd.secondTeamProject.dao.IOrderDAO;
import com.solvd.secondTeamProject.dao.IOrderGoodsDAO;
import com.solvd.secondTeamProject.dao.IProductDAO;
import com.solvd.secondTeamProject.dao.jdbc.OrderDAO;
import com.solvd.secondTeamProject.dao.jdbc.OrderGoodsDAO;
import com.solvd.secondTeamProject.dao.jdbc.ProductDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Product;

public class OrderService {
	private IOrderDAO oDAO = new OrderDAO();
	private IProductDAO pDAO = new ProductDAO();
	private IOrderGoodsDAO ogDAO = new OrderGoodsDAO();
	
	public Order save(Order o, Company c) {
		Order saved=  oDAO.save(o,c);
		for(Product p : o.getGoods()) {
			pDAO.save(p);
			ogDAO.relate(saved, p);
		}
		return saved;
	}
	
	public Order getOrder(long id) {
		Order result = oDAO.getOrderById(id);
		result.setGoods(ogDAO.getProductsByOrderId(id));
		return result;
	};

}

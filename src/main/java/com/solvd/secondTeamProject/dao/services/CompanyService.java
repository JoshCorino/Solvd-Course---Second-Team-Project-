package com.solvd.secondTeamProject.dao.services;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.dao.IOrderDAO;
import com.solvd.secondTeamProject.dao.jdbc.CompanyDAO;
import com.solvd.secondTeamProject.dao.jdbc.OrderDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.CompanyTransport;
import com.solvd.secondTeamProject.model.Order;

public class CompanyService {
	
	ICompanyDAO cDAO= new CompanyDAO();
	IOrderDAO oDAO= new OrderDAO();
	CompanyTransportService ctService= new CompanyTransportService();
	
	public Company save(Company c) {
		
		for (Order o : c.getOrders()) {
			oDAO.save(o, c.getId());
		}
		for (CompanyTransport t : c.getTransports()) {
			ctService.save(t);
		}
		return cDAO.save(c);
	}
	
	public Company getCompanyWithOrders(long id) {
		Company result=cDAO.getById(id);
		result.setOrders(oDAO.getOrdersByCompanyId(id));
		return result;
	}
	
}

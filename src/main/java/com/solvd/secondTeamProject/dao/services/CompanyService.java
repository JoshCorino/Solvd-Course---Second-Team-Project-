package com.solvd.secondTeamProject.dao.services;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.dao.IOrderDAO;
import com.solvd.secondTeamProject.dao.jdbc.CompanyDAO;
import com.solvd.secondTeamProject.dao.jdbc.CompanyTransportDAO;
import com.solvd.secondTeamProject.dao.jdbc.OrderDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Transport;

public class CompanyService {
	
	ICompanyDAO cDAO= new CompanyDAO();
	IOrderDAO oDAO= new OrderDAO();
	CompanyTransportDAO ctDAO= new CompanyTransportDAO();
	
	public Company save(Company c) {
		
		Company saved=cDAO.save(c);
		for (Order o : c.getOrders()) {
			oDAO.save(o, saved);
		}
		for (Transport t : saved.getTransports()) {
			ctDAO.relate(saved,t);
		}
		return saved;
	}
	
	public Company getCompany(long id) {
		Company result=cDAO.getCompanyById(id);
		result.setOrders(oDAO.getOrdersByCompanyId(id));
		result.setTransports(ctDAO.getTransportsByCompanyId(id));
		return result;
	}
	
}

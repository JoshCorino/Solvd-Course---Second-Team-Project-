package com.solvd.secondTeamProject.dao.mybatis;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IOrderDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Order;

public class OrderDAO extends MyBatisAbstractDAO implements IOrderDAO{
	private Logger log = LogManager.getLogger(CompanyDAO.class);

	@Override
	public Order getOrderById(long id) {
		IOrderDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderDAO.class);
		if(bhDao.getOrderById(id) != null)
			return bhDao.getOrderById(id);
		return new Order();
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Order save(Order g, Company c) {
		IOrderDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderDAO.class);
		bhDao.save(g, c);
		return g;
	}

	@Override
	public List<Order> getOrdersByCompanyId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;


import com.solvd.secondTeamProject.dao.IOrderDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Order;

public class OrderDAO extends MyBatisAbstractDAO implements IOrderDAO{

	@Override
	public Order getOrderById(long id) {
		IOrderDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderDAO.class);
		Order o = bhDao.getOrderById(id);
		if(o != null)
			return o;
		return new Order();
	}

	@Override
	public void remove(long id) {
		IOrderDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderDAO.class);
		bhDao.remove(id);
	}


	@Override
	public Order save(Order g, Company c) {
		IOrderDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderDAO.class);
		return bhDao.save(g, c);
	}

	@Override
	public List<Order> getOrdersByCompanyId(long id) {
		IOrderDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderDAO.class);
		List<Order> lo = bhDao.getOrdersByCompanyId(id);
		if(lo != null)
			return lo;
		return new ArrayList<Order>();
	}

}

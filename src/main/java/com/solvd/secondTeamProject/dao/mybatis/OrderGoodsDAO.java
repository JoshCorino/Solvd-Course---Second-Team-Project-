package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IOrderGoodsDAO;
import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Product;

public class OrderGoodsDAO extends MyBatisAbstractDAO implements IOrderGoodsDAO{
	private Logger log = LogManager.getLogger(OrderGoodsDAO.class);

	@Override
	public List<Product> getProductsByOrderId(long id) {
		IOrderGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderGoodsDAO.class);
		if(bhDao.getProductsByOrderId(id) != null)
			return bhDao.getProductsByOrderId(id);
		return new ArrayList<Product>();
	}

	@Override
	public void relate(Order o, Product p) {
		IOrderGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderGoodsDAO.class);
		bhDao.relate(o, p);
	}

}

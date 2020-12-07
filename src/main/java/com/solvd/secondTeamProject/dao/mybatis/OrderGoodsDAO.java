package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;


import com.solvd.secondTeamProject.dao.IOrderGoodsDAO;
import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Product;

public class OrderGoodsDAO extends MyBatisAbstractDAO implements IOrderGoodsDAO{

	@Override
	public List<Product> getProductsByOrderId(long id) {
		IOrderGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderGoodsDAO.class);
		List<Product> lp = bhDao.getProductsByOrderId(id);
		if(lp != null)
			return lp;
		return new ArrayList<Product>();
	}

	@Override
	public void relate(Order o, Product p) {
		IOrderGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderGoodsDAO.class);
		bhDao.relate(o, p);
	}

}

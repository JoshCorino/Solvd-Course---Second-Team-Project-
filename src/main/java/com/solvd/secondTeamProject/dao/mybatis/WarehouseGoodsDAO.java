package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;

import com.solvd.secondTeamProject.dao.IWarehouseGoodsDAO;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseGoodsDAO extends MyBatisAbstractDAO implements IWarehouseGoodsDAO {

	@Override
	public List<Product> getGoodsByWarehouseId(long id) {
		IWarehouseGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseGoodsDAO.class);
		List<Product> lp = bhDao.getGoodsByWarehouseId(id);
		if(lp != null)
			return lp;
		return new ArrayList<Product>();
	}

	@Override
	public void relate(Warehouse w, Product p) {
		IWarehouseGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseGoodsDAO.class);
		bhDao.relate(w, p);
	}

}

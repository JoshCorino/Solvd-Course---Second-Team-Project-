package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IWarehouseGoodsDAO;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseGoodsDAO extends MyBatisAbstractDAO implements IWarehouseGoodsDAO {
	private Logger log = LogManager.getLogger(WarehouseGoodsDAO.class);

	@Override
	public List<Product> getGoodsByWarehouseId(long id) {
		IWarehouseGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseGoodsDAO.class);
		if(bhDao.getGoodsByWarehouseId(id) != null)
			return bhDao.getGoodsByWarehouseId(id);
		return new ArrayList<Product>();
	}

	@Override
	public void relate(Warehouse w, Product p) {
		IWarehouseGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseGoodsDAO.class);
		bhDao.relate(w, p);
	}

}

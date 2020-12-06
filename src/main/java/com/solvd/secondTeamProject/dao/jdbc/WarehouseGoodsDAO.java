package com.solvd.secondTeamProject.dao.jdbc;

import java.util.List;

import com.solvd.secondTeamProject.dao.IWarehouseGoodsDAO;
import com.solvd.secondTeamProject.model.Product;

public class WarehouseGoodsDAO extends MySQLDAO implements IWarehouseGoodsDAO{

	private final String GET_WAREHOUSE= "select * from warehouses where id=?";
	
	@Override
	public List<Product> getGoodsByWarehouseId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

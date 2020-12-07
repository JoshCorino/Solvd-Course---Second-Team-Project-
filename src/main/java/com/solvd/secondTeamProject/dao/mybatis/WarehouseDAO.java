package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;


import com.solvd.secondTeamProject.dao.IWarehouseDAO;

import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseDAO extends MyBatisAbstractDAO implements IWarehouseDAO{


	@Override
	public Warehouse save(Warehouse g) {
		IWarehouseDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseDAO.class);
		bhDao.save(g);
		return g;
	}


	@Override
	public void remove(long id) {
		IWarehouseDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseDAO.class);
		bhDao.remove(id);
	}

	@Override
	public Warehouse getWarehouseById(long id) {
		IWarehouseDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseDAO.class);
		Warehouse wh = bhDao.getWarehouseById(id);
		if(wh != null)
			return wh;
		return new Warehouse();
	}


	@Override
	public List<Warehouse> getAll() {
		IWarehouseDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseDAO.class);
		List<Warehouse> lw = bhDao.getAll();
		if(lw != null)
			return lw;
		return new ArrayList<Warehouse>();
	}

}

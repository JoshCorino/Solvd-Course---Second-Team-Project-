package com.solvd.secondTeamProject.dao.mybatis;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.secondTeamProject.dao.IWarehouseDAO;

import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseDAO extends MyBatisAbstractDAO implements IWarehouseDAO{
	private Logger log = LogManager.getLogger(CompanyDAO.class);


	@Override
	public Warehouse save(Warehouse g) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Warehouse getWarehouseById(long id) {
		IWarehouseDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseDAO.class);
		if(bhDao.getWarehouseById(id) != null)
			return bhDao.getWarehouseById(id);
		return new Warehouse();
	}


	@Override
	public List<Warehouse> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

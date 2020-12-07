package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IWarehouseTransportsDAO;
import com.solvd.secondTeamProject.model.Transport;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseTransportsDAO extends MyBatisAbstractDAO implements IWarehouseTransportsDAO {
	private Logger log = LogManager.getLogger(WarehouseTransportsDAO.class);

	@Override
	public List<Transport> getTransportsByWarehouseId(long id) {
		IWarehouseTransportsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseTransportsDAO.class);
		if(bhDao.getTransportsByWarehouseId(id) != null)
			return bhDao.getTransportsByWarehouseId(id);
		return new ArrayList<Transport>();
	}

	@Override
	public void relate(Warehouse w, Transport t) {
		IWarehouseTransportsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseTransportsDAO.class);
		bhDao.relate(w, t);
	}

}

package com.solvd.secondTeamProject.dao.mybatis;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ITransportDAO;
import com.solvd.secondTeamProject.model.Transport;

public class TransportDAO extends MyBatisAbstractDAO implements ITransportDAO{
	private Logger log = LogManager.getLogger(TransportDAO.class);

	@Override
	public Transport save(Transport g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transport getById(long id) {
		ITransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ITransportDAO.class);
		if(bhDao.getById(id) != null)
			return bhDao.getById(id);
		return new Transport();
	}
	

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}

}

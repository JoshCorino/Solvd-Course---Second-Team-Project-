package com.solvd.secondTeamProject.dao.mybatis;



import com.solvd.secondTeamProject.dao.ITransportDAO;
import com.solvd.secondTeamProject.model.Transport;

public class TransportDAO extends MyBatisAbstractDAO implements ITransportDAO{

	@Override
	public Transport save(Transport g) {
		ITransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ITransportDAO.class);
		bhDao.save(g);
		return g;
	}

	@Override
	public Transport getById(long id) {
		ITransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ITransportDAO.class);
		Transport t = bhDao.getById(id);
		if(t != null)
			return t;
		return new Transport();
	}
	

	@Override
	public void remove(long id) {
		ITransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ITransportDAO.class);
		bhDao.remove(id);
	}

}

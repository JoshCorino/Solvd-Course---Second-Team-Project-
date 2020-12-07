package com.solvd.secondTeamProject.dao.mybatis;

import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ICompanyTransportDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Transport;

public class CompanyTransportDAO extends MyBatisAbstractDAO implements ICompanyTransportDAO {
	private Logger log = LogManager.getLogger(CompanyTransportDAO.class);

	@Override
	public List<Transport> getTransportsByCompanyId(long id) {
		ICompanyTransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyTransportDAO.class);
		if(bhDao.getTransportsByCompanyId(id) != null)
			return bhDao.getTransportsByCompanyId(id);
		return new ArrayList<Transport>();
	}

	@Override
	public void relate(Company c, Transport t) {
		ICompanyTransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyTransportDAO.class);
		bhDao.relate(c, t);
	}

}

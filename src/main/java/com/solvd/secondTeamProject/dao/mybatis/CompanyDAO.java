package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.model.Company;

public class CompanyDAO extends MyBatisAbstractDAO implements ICompanyDAO{
	private Logger log = LogManager.getLogger(CompanyDAO.class);


	public Company save(Company g) {
		ICompanyDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyDAO.class);
		bhDao.save(g);
		return g;
	}

	public Company getCompanyById(long id) {
		ICompanyDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyDAO.class);
		if(bhDao.getCompanyById(id) != null)
			return bhDao.getCompanyById(id);
		return new Company();
	}

	public void remove(long id) {
		ICompanyDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyDAO.class);
		bhDao.remove(id);
	}

	@Override
	public List<Company> getAll() {
		ICompanyDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyDAO.class);
		if(bhDao.getAll() != null)
			return bhDao.getAll();
		return new ArrayList<Company>();
	}

}

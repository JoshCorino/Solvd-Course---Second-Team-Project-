package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.model.Company;

public class CompanyDAO extends MyBatisAbstractDAO implements ICompanyDAO{

	public Company save(Company g) {
		ICompanyDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyDAO.class);
		return bhDao.save(g);
	}

	public Company getCompanyById(long id) {
		ICompanyDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyDAO.class);
		Company c = bhDao.getCompanyById(id);
		if(c != null)
			return c;
		return new Company();
	}

	public void remove(long id) {
		ICompanyDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyDAO.class);
		bhDao.remove(id);
	}

	@Override
	public List<Company> getAll() {
		ICompanyDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyDAO.class);
		List<Company> lc = bhDao.getAll();
		if(lc != null)
			return lc;
		return new ArrayList<Company>();
	}

}

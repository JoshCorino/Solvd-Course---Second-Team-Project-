package com.solvd.secondTeamProject.dao.jdbc;

import java.util.List;

import com.solvd.secondTeamProject.dao.ICompanyTransportDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Transport;

public class CompanyTransportDAO extends MySQLDAO implements ICompanyTransportDAO{

	@Override
	public List<Transport> getTransportsByCompanyId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void relate(Company c, Transport t) {
		// TODO Auto-generated method stub
		
	}

}

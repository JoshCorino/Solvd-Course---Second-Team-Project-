package com.solvd.secondTeamProject.dao.mybatis;

import java.util.ArrayList;
import java.util.List;



import com.solvd.secondTeamProject.dao.ICompanyTransportDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Transport;

public class CompanyTransportDAO extends MyBatisAbstractDAO implements ICompanyTransportDAO {

	@Override
	public List<Transport> getTransportsByCompanyId(long id) {
		ICompanyTransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyTransportDAO.class);
		List<Transport> lt = bhDao.getTransportsByCompanyId(id);
		if(lt != null)
			return lt;
		return new ArrayList<Transport>();
	}

	@Override
	public void relate(Company c, Transport t) {
		ICompanyTransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyTransportDAO.class);
		bhDao.relate(c, t);
	}

}

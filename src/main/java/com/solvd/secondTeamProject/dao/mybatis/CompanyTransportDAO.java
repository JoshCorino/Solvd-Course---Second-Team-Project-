package com.solvd.secondTeamProject.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.dao.ICompanyTransportDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Transport;

public class CompanyTransportDAO implements ICompanyTransportDAO {
	private Logger log = LogManager.getLogger(CompanyTransportDAO.class);

	@Override
	public List<Transport> getTransportsByCompanyId(long id) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			ICompanyTransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyTransportDAO.class);
			if(bhDao.getTransportsByCompanyId(id) != null)
				return bhDao.getTransportsByCompanyId(id);
		} catch (IOException e) {
			log.error(e);
		}
		return new ArrayList<Transport>();
	}

	@Override
	public void relate(Company c, Transport t) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			ICompanyTransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ICompanyTransportDAO.class);
			bhDao.relate(c, t);
		} catch (IOException e) {
			log.error(e);
		}
	}

}

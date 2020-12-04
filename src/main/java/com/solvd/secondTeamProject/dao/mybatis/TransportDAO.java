package com.solvd.secondTeamProject.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.dao.ITransportDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Transport;

public class TransportDAO implements ITransportDAO{
	private Logger log = LogManager.getLogger(TransportDAO.class);

	@Override
	public Transport save(Transport g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transport getById(long id) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			ITransportDAO bhDao = sqlSessionFactory.openSession(true).getMapper(ITransportDAO.class);
			if(bhDao.getById(id) != null)
				return bhDao.getById(id);
		} catch (IOException e) {
			log.error(e);
		}
		return new Transport();
	}
	

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}

}

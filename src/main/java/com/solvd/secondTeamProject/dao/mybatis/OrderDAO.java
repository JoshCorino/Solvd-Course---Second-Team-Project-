package com.solvd.secondTeamProject.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.dao.IOrderDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Order;

public class OrderDAO implements IOrderDAO{
	private Logger log = LogManager.getLogger(CompanyDAO.class);

	@Override
	public Order getOrderById(long id) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			IOrderDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderDAO.class);
			if(bhDao.getOrderById(id) != null)
				return bhDao.getOrderById(id);
		} catch (IOException e) {
			log.error(e);
		}
		return new Order();
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Order save(Order g, Company c) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			IOrderDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderDAO.class);
			bhDao.save(g, c);
		} catch (IOException e) {
			log.error(e);
		}
		return g;
	}

}

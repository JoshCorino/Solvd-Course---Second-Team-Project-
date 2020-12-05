package com.solvd.secondTeamProject.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.dao.IWarehouseDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseDAO implements IWarehouseDAO{
	private Logger log = LogManager.getLogger(CompanyDAO.class);


	@Override
	public Warehouse save(Warehouse g) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Warehouse getWarehouseById(long id) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			IWarehouseDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseDAO.class);
			if(bhDao.getWarehouseById(id) != null)
				return bhDao.getWarehouseById(id);
		} catch (IOException e) {
			log.error(e);
		}
		return new Warehouse();
	}

}

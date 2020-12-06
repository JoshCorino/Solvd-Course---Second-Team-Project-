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

import com.solvd.secondTeamProject.dao.IWarehouseTransportsDAO;
import com.solvd.secondTeamProject.model.Transport;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseTransportsDAO implements IWarehouseTransportsDAO {
	private Logger log = LogManager.getLogger(WarehouseTransportsDAO.class);

	@Override
	public List<Transport> getTransportsByWarehouseId(long id) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			IWarehouseTransportsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseTransportsDAO.class);
			if(bhDao.getTransportsByWarehouseId(id) != null)
				return bhDao.getTransportsByWarehouseId(id);
		} catch (IOException e) {
			log.error(e);
		}
		return new ArrayList<Transport>();
	}

	@Override
	public void relate(Warehouse w, Transport t) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			IWarehouseTransportsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseTransportsDAO.class);
			bhDao.relate(w, t);
		} catch (IOException e) {
			log.error(e);
		}
	}

}

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

import com.solvd.secondTeamProject.dao.IWarehouseGoodsDAO;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseGoodsDAO implements IWarehouseGoodsDAO {
	private Logger log = LogManager.getLogger(WarehouseGoodsDAO.class);

	@Override
	public List<Product> getGoodsByWarehouseId(long id) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			IWarehouseGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseGoodsDAO.class);
			if(bhDao.getGoodsByWarehouseId(id) != null)
				return bhDao.getGoodsByWarehouseId(id);
		} catch (IOException e) {
			log.error(e);
		}
		return new ArrayList<Product>();
	}

	@Override
	public void relate(Warehouse w, Product p) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			IWarehouseGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseGoodsDAO.class);
			bhDao.relate(w, p);
		} catch (IOException e) {
			log.error(e);
		}
		
	}

}

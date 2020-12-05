package com.solvd.secondTeamProject.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IProductDAO;
import com.solvd.secondTeamProject.dao.IWarehouseGoodsDAO;
import com.solvd.secondTeamProject.model.Product;

public class ProductDAO implements IProductDAO{
	private Logger log = LogManager.getLogger(ProductDAO.class);

	@Override
	public Product save(Product g) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Product getProductById(long id) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			IProductDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IProductDAO.class);
			if(bhDao.getProductById(id) != null)
				return bhDao.getProductById(id);
		} catch (IOException e) {
			log.error(e);
		}
		return new Product();
	}

}

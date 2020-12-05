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
import com.solvd.secondTeamProject.dao.IOrderGoodsDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Product;

public class OrderGoodsDAO implements IOrderGoodsDAO{
	private Logger log = LogManager.getLogger(OrderGoodsDAO.class);

	@Override
	public List<Product> getProductsByOrderId(long id) {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			IOrderGoodsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IOrderGoodsDAO.class);
			if(bhDao.getProductsByOrderId(id) != null)
				return bhDao.getProductsByOrderId(id);
		} catch (IOException e) {
			log.error(e);
		}
		return new ArrayList<Product>();
	}

}

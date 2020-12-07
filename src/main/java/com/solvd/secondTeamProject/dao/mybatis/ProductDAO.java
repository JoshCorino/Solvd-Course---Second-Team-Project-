package com.solvd.secondTeamProject.dao.mybatis;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IProductDAO;
import com.solvd.secondTeamProject.model.Product;

public class ProductDAO extends MyBatisAbstractDAO implements IProductDAO{
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
		IProductDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IProductDAO.class);
		if(bhDao.getProductById(id) != null)
			return bhDao.getProductById(id);
		return new Product();
	}

}

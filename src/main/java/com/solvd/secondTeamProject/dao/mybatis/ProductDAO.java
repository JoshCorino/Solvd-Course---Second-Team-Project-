package com.solvd.secondTeamProject.dao.mybatis;


import com.solvd.secondTeamProject.dao.IProductDAO;
import com.solvd.secondTeamProject.model.Product;

public class ProductDAO extends MyBatisAbstractDAO implements IProductDAO{

	@Override
	public Product save(Product g) {
		IProductDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IProductDAO.class);
		bhDao.save(g);
		return g;
	}



	@Override
	public void remove(long id) {
		IProductDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IProductDAO.class);
		bhDao.remove(id);
	}


	@Override
	public Product getProductById(long id) {
		IProductDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IProductDAO.class);
		Product p = bhDao.getProductById(id);
		if(p != null)
			return p;
		return new Product();
	}

}

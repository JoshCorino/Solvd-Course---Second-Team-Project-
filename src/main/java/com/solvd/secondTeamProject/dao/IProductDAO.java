package com.solvd.secondTeamProject.dao;

import com.solvd.secondTeamProject.model.Product;

public interface IProductDAO{

	Product save(Product p);

	Product getById(long id);

	void remove(long id);

}

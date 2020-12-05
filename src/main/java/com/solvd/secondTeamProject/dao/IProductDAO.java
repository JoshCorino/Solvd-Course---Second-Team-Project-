package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Product;

public interface IProductDAO{

	Product save(Product p);

	@Select("SELECT * FROM goods WHERE id = #{id}")
	@ResultMap("ProductResultMap")
	Product getProductById(long id);

	void remove(long id);

}

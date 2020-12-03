package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Order;

public interface IOrderDAO  {

	public Order save(Order g, long companiesId);

	@Select("SELECT * FROM orders WHERE id = #{id}")
	@ResultMap("OrderResultMap")
	Order getById(long id);

	
	@Delete("DELETE FROM orders WHERE id = #{id}")
	void remove(long id);
	
}

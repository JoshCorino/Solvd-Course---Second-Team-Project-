package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Order;

public interface IOrderDAO extends IGenericDAO<Order> {

	@Override
	default Order save(Order g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Select("SELECT * FROM orders WHERE id = #{id}")
	@ResultMap("OrderResultMap")
	Order getById(long id);

	@Override
	@Delete("DELETE FROM orders WHERE id = #{id}")
	void remove(long id);
	
}

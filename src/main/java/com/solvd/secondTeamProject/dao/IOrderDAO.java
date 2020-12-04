package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Order;

public interface IOrderDAO {

	@Insert("INSERT INTO order (companies_id, date) VALUES ")
	Order save(Order g);

	@Select("SELECT * FROM orders WHERE id = #{id}")
	@ResultMap("OrderResultMap")
	Order getById(long id);

	@Delete("DELETE FROM orders WHERE id = #{id}")
	void remove(long id);
	
}

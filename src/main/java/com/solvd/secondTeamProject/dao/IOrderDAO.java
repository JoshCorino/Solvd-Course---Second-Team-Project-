package com.solvd.secondTeamProject.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Order;

public interface IOrderDAO {

	@Insert("INSERT INTO order (companies_id, date) VALUES (#{com.id}, #{ord.date})")
	Order save(@Param("ord") Order g, @Param("com") Company c);

	@Select("SELECT * FROM orders WHERE id = #{id}")
	@ResultMap("OrderResultMap")
	Order getOrderById(long id);

	@Delete("DELETE FROM orders WHERE id = #{id}")
	void remove(long id);

	@Select("SELECT * FROM orders WHERE companies_id = #{id}")
	List<Order> getOrdersByCompanyId(long id);
	

}

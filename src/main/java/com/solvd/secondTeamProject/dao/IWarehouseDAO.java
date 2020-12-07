package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Warehouse;

public interface IWarehouseDAO{

	@Insert("INSERT INTO warehouses "
			+ "(wh_name) "
			+ "VALUES (#{wh.name})")
	Warehouse save(@Param("wh") Warehouse w);

	@Select("SELECT * "
			+ "FROM warehouses wh "
			+ "WHERE wh.id = #{id}")
	@ResultMap("WarehouseResultMap")
	Warehouse getWarehouseById(long id);

	@Delete("DELETE FROM warehouses WHERE id = #{id}")
	void remove(long id);
	
	@Select("SELECT * FROM warehouses wh")
	@ResultMap("WarehouseResultMap")
	List<Warehouse> getAll();
}

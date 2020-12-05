package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Warehouse;

public interface IWarehouseDAO{

	Warehouse save(Warehouse w);

	@Select("SELECT * "
			+ "FROM warehouses wh "
			+ "WHERE wh.id = #{id}")
	@ResultMap("WarehouseResultMap")
	Warehouse getWarehouseById(long id);

	void remove(long id);

}

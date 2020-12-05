package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Warehouse;

public interface IWarehouseDAO{

	Warehouse save(Warehouse w);

	@Select("SELECT "
			+ "wh.wh_name, "
			+ "wh.id,"
			+ "gds.id			AS good_id,"
			+ "gds.price		AS good_price,"
			+ "gds.volume		AS good_volume,"
			+ "gds.good_name	AS good_good_name "
			+ "FROM warehouses wh "
			+ "LEFT JOIN warehouses_have_goods whg "
			+ "ON (wh.id = whg.warehouses_id) "
			+ "LEFT JOIN goods gds "
			+ "ON (gds.id = whg.goods_id) "
			+ "WHERE wh.id = #{id}")
	@ResultMap("WarehouseResultMap")
	Warehouse getWarehouseById(long id);

	void remove(long id);

}

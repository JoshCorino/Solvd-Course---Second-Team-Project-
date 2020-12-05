package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Product;

public interface IWarehouseGoodsDAO {
	
	@Select("SELECT "
			+ "gds.id, "
			+ "gds.good_name, "
			+ "gds.volume, "
			+ "gds.quantity, "
			+ "gds.price "
			+ "FROM warehouses_have_goods whg "
			+ "LEFT JOIN goods gds "
			+ "ON (whg.goods_id = gds.id) "
			+ "WHERE whg.warehouses_id = #{id}")
	@ResultMap("com.solvd.secondTeamProject.dao.IProductDAO.ProductResultMap")
	public List<Product> getGoodsByWarehouseId(long id);
}

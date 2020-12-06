package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Warehouse;

public interface IWarehouseGoodsDAO {
	
	@Select("SELECT "
			+ "gds.id, "
			+ "gds.good_name, "
			+ "gds.volume, "
			+ "whg.quantity, "
			+ "gds.price "
			+ "FROM warehouses_have_goods whg "
			+ "LEFT JOIN goods gds "
			+ "ON (whg.goods_id = gds.id) "
			+ "WHERE whg.warehouses_id = #{id}")
	@ResultMap("com.solvd.secondTeamProject.dao.IProductDAO.ProductResultMap")
	public List<Product> getGoodsByWarehouseId(long id);
	
	@Insert("INSERT INTO "
			+ "warehouses_have_goods "
			+ "(goods_id, warehouses_id, quantity) "
			+ "VALUES "
			+ "(#{pr.id}, #{wh.id}, #{pr.quantity})")
	public void relate(@Param("wh") Warehouse w, @Param("pr") Product p);
}

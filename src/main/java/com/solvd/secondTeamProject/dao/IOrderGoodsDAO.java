package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Product;

public interface IOrderGoodsDAO {


	@Select("SELECT * FROM orders_have_goods ohg "
			+ "LEFT JOIN goods gs "
			+ "ON (gs.id = ohg.goods_id) "
			+ "WHERE ohg.orders_id = #{id}")
	@ResultMap("com.solvd.secondTeamProject.dao.IProductDAO.ProductResultMap")
	List<Product> getProductsByOrderId(long id);
	
	@Insert("INSER INTO "
			+ "orders_have_goods "
			+ "(goods_id, orders_id, quantity) VALUES "
			+ "(#{pro.id}, #{ord.id}, #{pro.quantity})")
	void relate(@Param("ord") Order o, @Param("pro") Product p);
	
}

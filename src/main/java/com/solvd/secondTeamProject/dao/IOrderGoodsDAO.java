package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Product;

public interface IOrderGoodsDAO {


	@Select("SELECT * FROM orders WHERE id = #{id}")
	@ResultMap("OrderResultMap")
	List<Product> getProductsByOrderId(long id);
	
}

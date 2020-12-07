package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Product;

public interface IProductDAO{

	@Select("INSERT INTO goods "
			+ "(price, volume, good_name) "
			+ "VALUES (#{pro.price}, #{pro.volume}, #{pro.name})")
	Product save(@Param("pro") Product p);

	@Select("SELECT * FROM goods WHERE id = #{id}")
	@ResultMap("ProductResultMap")
	Product getProductById(long id);

	@Delete("DELETE FROM goods WHERE id = #{id}")
	void remove(long id);

}

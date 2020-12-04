package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Company;

public interface ICompanyDAO{
	
	@Select("insert into companies (name) values (#{name})")
	public Company save(Company g);
	
	@Select("SELECT "
			+ "com.name, com.id, "
			+ "ord.id 				AS order_id, "
			+ "ord.companies_id 	AS order_companies_id, "
			+ "ord.date 			AS order_date "
			+ "FROM companies com LEFT JOIN orders ord "
			+ "ON (ord.companies_id = com.id) "
			+ "WHERE com.id = #{id}")
	@ResultMap("CompanyResultMap")
	public Company getById(long id);
	
	@Select("SELECT * FROM companies")
	@ResultMap("CompanyResultMap")
	public List<Company> getAll();
	
	@Delete("delete from companies where id = #{id}")
	public void remove(long id);
}

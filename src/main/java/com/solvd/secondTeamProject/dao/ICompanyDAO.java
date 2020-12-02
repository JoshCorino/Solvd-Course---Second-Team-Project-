package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Company;

public interface ICompanyDAO extends IGenericDAO<Company>{
	
	@Select("insert into companies(name) values (#{name})")
	public Company save(Company g);
	
	@Select("SELECT * FROM companies com WHERE com.id = #{id}")
	@ResultMap("CompanyResultMap")
	public Company getById(long id);
	
	@Delete("delete from companies where id = #{id}")
	public void remove(long id);
}

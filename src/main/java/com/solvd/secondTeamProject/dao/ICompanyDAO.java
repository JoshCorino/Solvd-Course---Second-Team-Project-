package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Company;

public interface ICompanyDAO{
	
	@Select("insert into companies (name) values (#{name})")
	public Company save(Company g);
	
	@Select("SELECT * WHERE com.id = #{id}")
	@ResultMap("CompanyResultMap")
	public Company getCompanyById(long id);
	
	@Select("SELECT * FROM companies")
	@ResultMap("CompanyResultMap")
	public List<Company> getAll();
	
	@Delete("delete from companies where id = #{id}")
	public void remove(long id);
}

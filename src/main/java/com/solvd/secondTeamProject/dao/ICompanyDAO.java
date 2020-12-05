package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Company;

public interface ICompanyDAO{
	
	@Select("INSERT INTO companies (name) VALUES (#{name})")
	public Company save(Company g);
	
	@Select("SELECT * FROM companies com WHERE com.id = #{id}")
	@ResultMap("CompanyResultMap")
	public Company getCompanyById(long id);
	
	@Select("SELECT * FROM companies")
	@ResultMap("CompanyResultMap")
	public List<Company> getAll();
	
	@Delete("DELETE FROM companies WHERE id = #{id}")
	public void remove(long id);
}

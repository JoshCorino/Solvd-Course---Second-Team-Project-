package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Transport;

public interface ITransportDAO{
	
	@Insert("INSERT INTO transport_type "
			+ "(transport_name) "
			+ "VALUES (#{tra.name})")
	Transport save(@Param("tra") Transport g);
	
	@Select("SELECT * FROM transport_types WHERE id = #{id}")
	@ResultMap("TransportResultMap")
	Transport getById(long id);
	
	@Delete("DELETE FROM goods WHERE id = #{id}")
	void remove(long id);

	
}

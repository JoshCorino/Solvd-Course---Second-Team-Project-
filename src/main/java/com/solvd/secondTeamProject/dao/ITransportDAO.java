package com.solvd.secondTeamProject.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Transport;

public interface ITransportDAO{

	Transport save(Transport g);
	
	@Select("SELECT * FROM transport_types WHERE id = #{id}")
	@ResultMap("TransportResultMap")
	Transport getById(long id);

	void remove(long id);

	
}

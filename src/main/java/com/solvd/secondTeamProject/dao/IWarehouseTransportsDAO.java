package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Transport;

public interface IWarehouseTransportsDAO {
	
	@Select("SELECT "
			+ "tt.transport_name, "
			+ "tt.id "
			+ "FROM allowed_transports at "
			+ "LEFT JOIN transport_types tt "
			+ "ON (at.transport_types_id = tt.id) "
			+ "WHERE at.warehouses_id = #{id}")
	@ResultMap("com.solvd.secondTeamProject.dao.ITransportDAO.TransportResultMap")
	public List<Transport> getTransportsByWarehouseId(long id);
}

package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Transport;
import com.solvd.secondTeamProject.model.Warehouse;

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
	
	@Insert("INSERT INTO "
			+ "allowed_transports "
			+ "(warehouses_id, transport_type_id) "
			+ "VALUES "
			+ "(#{wh.id}, #{tr.id})")
	public void relate(@Param("wh") Warehouse w, @Param("tr") Transport t);
}

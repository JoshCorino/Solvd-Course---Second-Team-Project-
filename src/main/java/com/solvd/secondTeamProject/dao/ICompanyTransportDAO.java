package com.solvd.secondTeamProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.solvd.secondTeamProject.model.Transport;

public interface ICompanyTransportDAO {
	
	@Select("SELECT * FROM companies_have_transports cht "
			+ "LEFT JOIN transport_types tt "
			+ "ON (cht.transports_id = tt.id) "
			+ "WHERE cht.companies_id = #{id}")
	
	@ResultMap("com.solvd.secondTeamProject.dao.ITransportDAO.TransportResultMap")
	public List<Transport> getTransportsByCompanyId(long id);
}

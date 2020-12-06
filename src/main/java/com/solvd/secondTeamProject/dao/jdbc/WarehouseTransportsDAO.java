package com.solvd.secondTeamProject.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IWarehouseTransportsDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Transport;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseTransportsDAO extends MySQLDAO implements IWarehouseTransportsDAO{

	private final String GET_TRANSPORTS_BY_WAREHOUSE_ID=  "select t.* "
														+ "from transport_types t "
														+ "join allowed_transports a on (t.id=a.transport_types_id)"
														+ "where a.warehouses_id=?";
	
	private final String RELATE_WAREHOUSE_AND_TRANSPORTS= "insert into allowed_transports(warehouses_id,transport_types_id) values(?,?)";

	
	private Logger log = LogManager.getLogger(WarehouseTransportsDAO.class);

	@Override
	public List<Transport> getTransportsByWarehouseId(long id) {
		ArrayList<Transport> result = new ArrayList<Transport>();
		Connection con = null;
		try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(GET_TRANSPORTS_BY_WAREHOUSE_ID);
			pre.setLong(1,id);
			ResultSet rset = pre.executeQuery();
			while (rset.next()) {
				Transport t =new Transport();
				t.setName(rset.getString("transport_name"));
				t.setId(rset.getLong("id"));
				result.add(t);
			}
        } catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return result;
	}

	@Override
	public void relate(Warehouse w, Transport t) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(RELATE_WAREHOUSE_AND_TRANSPORTS, Statement.RETURN_GENERATED_KEYS);
			pre.setLong(1,w.getId());
			pre.setLong(2,t.getId());
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("Warehouse and Transport related");

		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
		
	}

}

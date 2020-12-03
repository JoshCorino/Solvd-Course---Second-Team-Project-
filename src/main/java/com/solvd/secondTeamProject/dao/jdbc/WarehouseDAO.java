package com.solvd.secondTeamProject.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IWarehouseDAO;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseDAO extends MySQLDAO implements IWarehouseDAO{
	
	private final String GET_WAREHOUSE= "select * from warehouses where id=?";
	private final String REMOVE_WAREHOUSE= "delete from warehouses where id=?";
	private final String SAVE_WAREHOUSE= "insert into warehouses(wh_name) values(?)";
	
	private Logger log = LogManager.getLogger(WarehouseDAO.class);
	
	@Override
	public Warehouse save(Warehouse w) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(SAVE_WAREHOUSE, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1,w.getName());
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("Warehouse saved");
            ResultSet rs = pre.getGeneratedKeys();
            if(rs.next())
            {
                w.setId(rs.getInt(1));
            }
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return w;
	}

	@Override
	public Warehouse getById(long id) {
		Warehouse w = new Warehouse();
		Connection con = null;
		try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(GET_WAREHOUSE);
			pre.setLong(1,id);
			ResultSet rset = pre.executeQuery();
			if (rset.next()) {
				w.setId(rset.getLong("id"));
				w.setName(rset.getString("wh_name"));
			}
        } catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return w;
	}

	@Override
	public void remove(long id) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(REMOVE_WAREHOUSE);
			pre.setLong(1,id);
			int rset = pre.executeUpdate();
			if(rset!=0)
				log.info("Warehouse deleted");
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
	}

}

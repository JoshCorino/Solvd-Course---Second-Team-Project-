package com.solvd.secondTeamProject.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ITransportDAO;
import com.solvd.secondTeamProject.model.Transport;

public class TransportDAO extends MySQLDAO implements ITransportDAO{
	
	private final String GET_TRANSPORT= "select * from transport_types where id=?";
	private final String REMOVE_TRANSPORT= "delete from transport_types where id=?";
	private final String SAVE_TRANSPORT= "insert into transport_types(transport_name) values(?)";
	private Logger log = LogManager.getLogger(TransportDAO.class);
	
	@Override
	public Transport save(Transport t) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(SAVE_TRANSPORT, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1,t.getName());
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("Transport saved");
            ResultSet rs = pre.getGeneratedKeys();
            if(rs.next())
            {
                t.setId(rs.getInt(1));
            }
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return t;
	}

	@Override
	public Transport getById(long id) {
		Transport t = new Transport();
		Connection con = null;
		try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(GET_TRANSPORT);
			pre.setLong(1,id);
			ResultSet rset = pre.executeQuery();
			if (rset.next()) {
				t.setId(rset.getLong("id"));
				t.setName(rset.getString("transport_name"));
			}
        } catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return t;
	}

	@Override
	public void remove(long id) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(REMOVE_TRANSPORT);
			pre.setLong(1,id);
			int rset = pre.executeUpdate();
			if(rset!=0)
				log.info("Transport deleted");
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
	}

}

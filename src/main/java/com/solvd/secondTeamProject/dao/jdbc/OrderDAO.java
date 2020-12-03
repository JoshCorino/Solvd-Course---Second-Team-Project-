package com.solvd.secondTeamProject.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IOrderDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Order;

public class OrderDAO extends MySQLDAO implements IOrderDAO{
	
	private final String GET_ORDER= "select * from orders where id=?";
	private final String REMOVE_ORDER= "delete from orders where id=?";
	private final String SAVE_ORDER= "insert into orders(companies_id,date) values(?,?)";
	
	private Logger log = LogManager.getLogger(OrderDAO.class);
	
	
	public Order save(Order o, long companiesId) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(SAVE_ORDER, Statement.RETURN_GENERATED_KEYS);
			pre.setLong(1,companiesId);
			pre.setDate(2,o.getDate());
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("Order saved");
            ResultSet rs = pre.getGeneratedKeys();
            if(rs.next())
            {
                o.setId(rs.getInt(1));
            }
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return o;
	}
	
	@Override
	public Order getById(long id) {
		Order o = new Order();
		Connection con = null;
		try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(GET_ORDER);
			pre.setLong(1,id);
			ResultSet rset = pre.executeQuery();
			if (rset.next()) {
				o.setId(rset.getLong("id"));
				o.setDate(rset.getDate("date"));
			}
        } catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return o;
	}

	@Override
	public void remove(long id) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(REMOVE_ORDER);
			pre.setLong(1,id);
			int rset = pre.executeUpdate();
			if(rset!=0)
				log.info("Order deleted");
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
	}



}

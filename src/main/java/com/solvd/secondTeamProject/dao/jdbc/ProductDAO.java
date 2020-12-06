package com.solvd.secondTeamProject.dao.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IProductDAO;
import com.solvd.secondTeamProject.model.Product;

public class ProductDAO extends MySQLDAO implements IProductDAO{
	
	private final String GET_PRODUCT= "select * from goods where id=?";
	private final String REMOVE_PRODUCT= "delete from goods where id=?";
	private final String SAVE_PRODUCT= "insert into goods(price,volume,good_name) values(?,?,?)";
	private Logger log = LogManager.getLogger(ProductDAO.class);
	
	@Override
	public Product save(Product p) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(SAVE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
			pre.setDouble(1,p.getPrice());
			pre.setDouble(2,p.getVolume());
			pre.setString(3,p.getName());
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("Product saved");
            ResultSet rs = pre.getGeneratedKeys();
            if(rs.next())
            {
                p.setId(rs.getInt(1));
            }
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return p;
	}

	
	@Override
	public Product getProductById(long id) {
		Product p = new Product();
		Connection con = null;
		try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(GET_PRODUCT);
			pre.setLong(1,id);
			ResultSet rset = pre.executeQuery();
			if (rset.next()) {
				p.setId(rset.getLong("id"));
				p.setName(rset.getString("good_name"));
				p.setPrice(rset.getDouble("price"));
				p.setVolume(rset.getDouble("volume"));
			}
        } catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return p;
	}

	@Override
	public void remove(long id) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(REMOVE_PRODUCT);
			pre.setLong(1,id);
			int rset = pre.executeUpdate();
			if(rset!=0)
				log.info("Product deleted");
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
	}



}

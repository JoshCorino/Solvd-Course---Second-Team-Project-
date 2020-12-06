package com.solvd.secondTeamProject.dao.jdbc;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.ICompanyDAO;
import com.solvd.secondTeamProject.model.Company;

public class CompanyDAO extends MySQLDAO implements ICompanyDAO{

	private final String GET_COMPANY= "select * from companies where id=?";
	private final String REMOVE_COMPANY= "delete from companies where id=?";
	private final String SAVE_COMPANY= "insert into companies(name) values(?)";
	private Logger log = LogManager.getLogger(CompanyDAO.class);

	
	@Override
	public Company save(Company c) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(SAVE_COMPANY, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1,c.getName());
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("Company saved");
            ResultSet rs = pre.getGeneratedKeys();
            if(rs.next())
            {
                c.setId(rs.getInt(1));
            }
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return c;
	}

	@Override
	public Company getCompanyById(long id) {
		Company c = new Company();
		Connection con = null;
		try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(GET_COMPANY);
			pre.setLong(1,id);
			ResultSet rset = pre.executeQuery();
			if (rset.next()) {
				c.setId(rset.getLong("id"));
				c.setName(rset.getString("name"));
			}
        } catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
        return c;
	}

	@Override
	public void remove(long id) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(REMOVE_COMPANY);
			pre.setLong(1,id);
			int rset = pre.executeUpdate();
			if(rset!=0)
				log.info("Company deleted");
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
	}

	@Override
	public List<Company> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}

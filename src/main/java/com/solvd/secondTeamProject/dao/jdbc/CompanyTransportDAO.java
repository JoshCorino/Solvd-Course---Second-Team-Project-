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

import com.solvd.secondTeamProject.dao.ICompanyTransportDAO;
import com.solvd.secondTeamProject.model.Company;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Transport;

public class CompanyTransportDAO extends MySQLDAO implements ICompanyTransportDAO{

	private final String GET_TRANSPORTS_BY_COMPANY_ID=    "select t.id, c.capacity, t.transport_name "
														+ "from companies_have_transports c "
														+ "join transport_types t on (t.id=c.transports_id)"
														+ "where c.companies_id=?";
	
	private final String RELATE_COMPANY_AND_TRANSPORT= "insert into companies_have_transports(companies_id,transports_id,capacity) values(?,?,?)";
	
	private Logger log = LogManager.getLogger(CompanyTransportDAO.class);

	@Override
	public List<Transport> getTransportsByCompanyId(long id) {
		ArrayList<Transport> result = new ArrayList<Transport>();
		Connection con = null;
		try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(GET_TRANSPORTS_BY_COMPANY_ID);
			pre.setLong(1,id);
			ResultSet rset = pre.executeQuery();
			while (rset.next()) {
				Transport t =new Transport();
				t.setName(rset.getString("transport_name"));
				t.setId(rset.getLong("id"));
				t.setCapacity(rset.getDouble("capacity"));
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
	public void relate(Company c, Transport t) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(RELATE_COMPANY_AND_TRANSPORT, Statement.RETURN_GENERATED_KEYS);
			pre.setLong(1,c.getId());
			pre.setLong(2,t.getId());
			pre.setDouble(2,t.getCapacity());
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("Company an transport related");

		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
		
	}

}

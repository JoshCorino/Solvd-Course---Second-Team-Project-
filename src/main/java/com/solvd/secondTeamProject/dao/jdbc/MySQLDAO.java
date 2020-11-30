package com.solvd.secondTeamProject.dao.jdbc;



import com.solvd.secondTeamProject.connection.ConnectionPool;

public abstract class MySQLDAO {
	protected ConnectionPool cp;

	public MySQLDAO() {
		this.cp = ConnectionPool.getInstance();
	}
	
}

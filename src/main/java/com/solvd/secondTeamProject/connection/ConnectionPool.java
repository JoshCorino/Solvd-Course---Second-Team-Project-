package com.solvd.secondTeamProject.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;

public class ConnectionPool  {

	private static ConnectionPool cp;
	private Logger log = LogManager.getLogger(ConnectionPool.class);
	private BlockingQueue<Connection> connections;
	private AtomicInteger connectionsCount;
	public static final Integer MAX_POOL_SIZE = 5;
	
	
	public ConnectionPool(){
		connections = new LinkedBlockingQueue<Connection>(MAX_POOL_SIZE);
		connectionsCount = new AtomicInteger(0);
	};

	public static ConnectionPool getInstance(){
		if(cp == null){
			synchronized (ConnectionPool.class){ 
				if(cp == null)
				{
					cp = new ConnectionPool();
				}
			}
		}
		return cp;
	}
	private void initConnection() {
		
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/database.properties"));
			Class.forName(prop.getProperty("DRIVER")).newInstance();
			connections.add(DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("USER_NAME"), prop.getProperty("PASSWORD")));
			connectionsCount.incrementAndGet();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | IOException e) {
			log.error("Cannot get a conection", e);
		}
					
	}

	public Connection getConnection() throws InterruptedException {
		synchronized (ConnectionPool.class){
			if(connections.size() == 0 && connectionsCount.get() < MAX_POOL_SIZE ) {
				initConnection();
			}
		}
		return connections.take();
	}
	
	public void releaseConnection(Connection connectionReleased) {
		connections.offer(connectionReleased);
	}
	
	public boolean closeAll() {

		connections.forEach(c -> {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("The pool cant close one connection",e);
			}
		});
		connections = new LinkedBlockingQueue<Connection>(MAX_POOL_SIZE);
		connectionsCount.set(0);
		return true;
	}

}
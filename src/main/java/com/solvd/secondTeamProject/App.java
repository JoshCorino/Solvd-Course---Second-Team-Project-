package com.solvd.secondTeamProject;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.solvd.secondTeamProject.algorithm.Algorithm;
import com.solvd.secondTeamProject.algorithm.ResultRepresentation;

import com.solvd.secondTeamProject.dao.jdbc.CompanyDAO;
import com.solvd.secondTeamProject.dao.mybatis.*;

import com.solvd.secondTeamProject.model.*;

public class App{
	
	
	public static void main( String[] args ){
    	Logger log = LogManager.getLogger(App.class);

     	List<Order> orders = null;
    	WarehouseDAO wDAO = new WarehouseDAO();
		List<Warehouse> warehouses = wDAO.getAll();
    	
		CompanyDAO cDAO = new CompanyDAO();
		Company company = cDAO.getCompanyById(1);
		
    	List<ResultRepresentation> bestTransports = Algorithm.bestTransports(orders,company,warehouses);
    	   	
    }
   
}

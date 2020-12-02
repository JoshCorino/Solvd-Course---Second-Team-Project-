package com.solvd.secondTeamProject;

import com.solvd.secondTeamProject.dao.mybatis.CompanyDAO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CompanyDAO dao = new CompanyDAO();
        System.out.println(dao.getById(1).getName());
    }
}

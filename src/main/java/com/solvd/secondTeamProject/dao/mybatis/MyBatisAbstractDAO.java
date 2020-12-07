package com.solvd.secondTeamProject.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MyBatisAbstractDAO {
	private Logger log = LogManager.getLogger(MyBatisAbstractDAO.class);
	protected SqlSessionFactory sqlSessionFactory;
	
	public MyBatisAbstractDAO() {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			log.error(e);
		}
	}
}

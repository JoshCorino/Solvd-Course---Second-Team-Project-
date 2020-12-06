package com.solvd.secondTeamProject.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.secondTeamProject.dao.IWarehouseGoodsDAO;
import com.solvd.secondTeamProject.model.Product;
import com.solvd.secondTeamProject.model.Transport;

public class WarehouseGoodsDAO extends MySQLDAO implements IWarehouseGoodsDAO{

	private final String GET_GOODS_BY_WAREHOUSE_ID=   "select g.* "
													+ "from warehouses_have_goods w "
													+ "join goods g on (g.id=w.goods_id)"
													+ "where w.warehouses_id=?";
	
	private Logger log = LogManager.getLogger(WarehouseGoodsDAO.class);

	
	@Override
	public List<Product> getGoodsByWarehouseId(long id) {
		ArrayList<Product> result = new ArrayList<Product>();
		Connection con = null;
		try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(GET_GOODS_BY_WAREHOUSE_ID);
			pre.setLong(1,id);
			ResultSet rset = pre.executeQuery();
			while (rset.next()) {
				Product p =new Product();
				p.setName(rset.getString("good_name"));
				p.setId(rset.getLong("id"));
				p.setPrice(rset.getDouble("price"));
				p.setVolume(rset.getDouble("volume"));
				result.add(p);
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

}

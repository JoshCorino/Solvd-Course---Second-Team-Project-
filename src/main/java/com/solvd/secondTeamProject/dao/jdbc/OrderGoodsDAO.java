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

import com.solvd.secondTeamProject.dao.IOrderGoodsDAO;
import com.solvd.secondTeamProject.model.Order;
import com.solvd.secondTeamProject.model.Product;

public class OrderGoodsDAO extends MySQLDAO implements IOrderGoodsDAO{
	
	private final String GET_PRODUCTS_BY_ORDER_ID=    "select g.*, o.quantity"
													+ "from goods g "
													+ "join orders_have_goods o on (g.id=o.goods_id)"
													+ "where o.orders_id=?";

	private final String RELATE_PRODUCT_AND_ORDER= "insert into orders_have_goods(goods_id,orders_id,quantity) values(?,?,?)";
	
	private Logger log = LogManager.getLogger(OrderGoodsDAO.class);

	@Override
	public List<Product> getProductsByOrderId(long id) {
		ArrayList<Product> result = new ArrayList<Product>();
		Connection con = null;
		try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(GET_PRODUCTS_BY_ORDER_ID);
			pre.setLong(1,id);
			ResultSet rset = pre.executeQuery();
			while (rset.next()) {
				Product p =new Product();
				p.setName(rset.getString("good_name"));
				p.setId(rset.getLong("id"));
				p.setPrice(rset.getDouble("price"));
				p.setVolume(rset.getDouble("volume"));
				p.setQuantity(rset.getLong("quantity"));
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

	@Override
	public void relate(Order o, Product p) {
		Connection con = null;
        try {
			con = cp.getConnection();
			PreparedStatement pre = con.prepareStatement(RELATE_PRODUCT_AND_ORDER, Statement.RETURN_GENERATED_KEYS);
			pre.setLong(1,p.getId());
			pre.setLong(2,o.getId());
			pre.setLong(3,p.getQuantity());			
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("Order and Product related");

		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			cp.releaseConnection(con);
		}
		
	}

}

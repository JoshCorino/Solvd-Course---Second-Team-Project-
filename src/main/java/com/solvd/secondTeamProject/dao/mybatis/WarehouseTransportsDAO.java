package com.solvd.secondTeamProject.dao.mybatis;


import java.util.ArrayList;
import java.util.List;



import com.solvd.secondTeamProject.dao.IWarehouseTransportsDAO;
import com.solvd.secondTeamProject.model.Transport;
import com.solvd.secondTeamProject.model.Warehouse;

public class WarehouseTransportsDAO extends MyBatisAbstractDAO implements IWarehouseTransportsDAO {

	@Override
	public List<Transport> getTransportsByWarehouseId(long id) {
		IWarehouseTransportsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseTransportsDAO.class);
		List<Transport> lt = bhDao.getTransportsByWarehouseId(id) ;
		if(lt != null)
			return lt;
		return new ArrayList<Transport>();
	}

	@Override
	public void relate(Warehouse w, Transport t) {
		IWarehouseTransportsDAO bhDao = sqlSessionFactory.openSession(true).getMapper(IWarehouseTransportsDAO.class);
		bhDao.relate(w, t);
	}

}

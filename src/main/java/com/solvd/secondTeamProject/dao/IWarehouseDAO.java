package com.solvd.secondTeamProject.dao;

import com.solvd.secondTeamProject.model.Warehouse;

public interface IWarehouseDAO{

	Warehouse save(Warehouse w);

	Warehouse getById(long id);

	void remove(long id);

}

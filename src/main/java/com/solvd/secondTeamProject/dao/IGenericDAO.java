package com.solvd.secondTeamProject.dao;

public interface IGenericDAO<T> {

	public T save(T g) ;
	public T getById(long id);
	public void remove(long id) ;

}

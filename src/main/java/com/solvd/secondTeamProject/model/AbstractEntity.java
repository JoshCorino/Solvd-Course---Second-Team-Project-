package com.solvd.secondTeamProject.model;



public abstract class AbstractEntity {
	public long id;

	public AbstractEntity() {
		this.id = 0;
	}
	
	public AbstractEntity(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}

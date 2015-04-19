package com.gbleague.league.model.league;

import com.gbleague.league.model.ILeague;

/**
 * The simple pojo class of the league model
 */
public class PojoLeague implements ILeague {
	int id;
	String name;
	int commissionerId;

	public PojoLeague(int id, String name, int commissionerId) {
		this.id = id;
		this.name = name;
		this.commissionerId = commissionerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCommissionerId() {
		return commissionerId;
	}

	public void setCommissionerId(int commissionerId) {
		this.commissionerId = commissionerId;
	}
}

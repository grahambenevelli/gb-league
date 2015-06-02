package com.gbleague.models.league;

import com.gbleague.models.AbstractModelWithId;
import com.gbleague.models.manager.Manager;

public class League extends AbstractModelWithId {

	private String name;
	private Long commissionerId;
	private Manager commissioner;
	
	public League() {}

	public League(String name, Manager commissioner) {
		this.name = name;
		this.commissioner = commissioner;
	}

	public String getName() {
		return name;
	}

	public League setName(String name) {
		this.name = name;
		return this;
	}

	public Manager getCommissioner() {
		return commissioner;
	}

	public League setCommissioner(Manager commissioner) {
		this.commissioner = commissioner;
		return this;
	}

	public Long getCommissionerId() {
		return commissionerId;
	}

	public League setCommissionerId(Long commissionerId) {
		this.commissionerId = commissionerId;
		return this;
	}

	@Override 
	public League setId(long id) {
		this.id = id;
		return this;
	}
}

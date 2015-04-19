package com.gbleague.web.dto.league;

import com.gbleague.league.model.ILeague;
import com.gbleague.league.model.league.PojoLeague;
import com.gbleague.web.dto.AbstractResponseDTO;

public class LeagueDTO extends AbstractResponseDTO<ILeague> {

	int id;
	String name;
	int commissionerId;

	public LeagueDTO(ILeague league) {
		this.copyFromModel(league);
	}

	@Override
	protected void copyFromModel(ILeague league) {
		this.id = league.getId();
		this.name = league.getName();
		this.commissionerId = league.getCommissionerId();
	}

	@Override
	protected ILeague copyToModel(ILeague league) {
		league.setId(this.id);
		league.setName(this.getName());
		league.setCommissionerId(this.getCommissionerId());
		return league;
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

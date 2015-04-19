package com.gbleague.web.dto.manager;

import com.gbleague.league.model.IManager;
import com.gbleague.web.dto.AbstractResponseDTO;

public class ManagerDTO  extends AbstractResponseDTO<IManager> {
	private long id;
	private String firstName;
	private String lastName;

	public ManagerDTO(IManager manager) {
		this.copyFromModel(manager);
	}

	@Override
	protected void copyFromModel(IManager manager) {
		this.setId(manager.getId());
		this.setFirstName(manager.getFirstName());
		this.setLastName(manager.getLastName());
	}

	@Override
	protected IManager copyToModel(IManager manager) {
		this.setId(manager.getId());
		this.setFirstName(manager.getFirstName());
		this.setLastName(manager.getLastName());
		return manager;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

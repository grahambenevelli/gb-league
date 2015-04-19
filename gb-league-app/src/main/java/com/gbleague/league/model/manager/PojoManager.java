package com.gbleague.league.model.manager;

import com.gbleague.league.model.IManager;

/**
 * Simple pojo class for a manager
 */
public class PojoManager implements IManager {

	private long id;
	private String firstName;
	private String lastName;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

package com.gbleague.models;

public abstract class AbstractModelWithId {

	protected long id;

	public long getId() {
		return id;
	}

	public abstract AbstractModelWithId setId(long id);

}

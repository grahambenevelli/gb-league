package com.gbleague.server.resources;

import com.google.common.base.Throwables;
import com.sun.jersey.api.NotFoundException;

import javax.ws.rs.core.Response;

public abstract class AbstractResource {
	
	protected Response handleException(Exception e) {
		if (e instanceof NotFoundException) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		throw Throwables.propagate(e);
	}
}

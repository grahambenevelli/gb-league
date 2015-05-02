package com.gbleague.server.resources.manager;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gbleague.manager.manager.ManagerManager;
import com.gbleague.models.manager.Manager;
import com.gbleague.server.resources.AbstractResource;
import com.yammer.dropwizard.auth.Auth;
import com.yammer.dropwizard.jersey.params.LongParam;

@Path("/manager/{managerId}")
@Produces(MediaType.APPLICATION_JSON)
public class ManagerByIdResource extends AbstractResource {
	
	private final ManagerManager managerManager;
	
	public ManagerByIdResource(ManagerManager managerManager) {
		this.managerManager = managerManager;
	}

	@GET
	public Response getManager(
			@Context HttpServletRequest request,
			@Auth Manager currentManager,
			@PathParam("managerId") LongParam personId) {
		try {
			Manager otherManager = managerManager.getManagerById(personId.get());
			return Response.ok(otherManager).build();
		} catch (Exception e) {
			return this.handleException(e);
		}
	}
	
	// TODO post
}

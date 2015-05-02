package com.gbleague.server.resources.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.yammer.dropwizard.jersey.params.LongParam;

@Path("/manager/{managerId}")
@Produces(MediaType.APPLICATION_JSON)
public class ManagerResource extends AbstractResource {
	
	private final ManagerManager managerManager;
	
	public ManagerResource(ManagerManager managerManager) {
		this.managerManager = managerManager;
	}

	@GET
	public Response getManager(@Context HttpServletRequest request, @PathParam("managerId") LongParam personId) {
		HttpSession session = request.getSession();
		Object o = session.getAttribute("currentManager");
		try {
			Manager manager = managerManager.getManagerById(personId.get());
			return Response.ok(manager).build();
		} catch (Exception e) {
			return this.handleException(e);
		}
	}
	
	// TODO post
}

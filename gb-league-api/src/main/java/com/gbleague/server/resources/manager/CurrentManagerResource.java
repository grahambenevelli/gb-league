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

@Path("/manager/current")
@Produces(MediaType.APPLICATION_JSON)
public class CurrentManagerResource extends AbstractResource {

	@GET
	public Response getManager(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object o = session.getAttribute("currentManager");
		if (o instanceof Manager) {
			return Response.ok(o).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
}

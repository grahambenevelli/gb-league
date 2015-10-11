package com.gbleague.server.resources.manager;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gbleague.manager.manager.ManagerManager;
import com.gbleague.models.manager.CreateManagerRequest;
import com.gbleague.models.manager.Manager;
import com.gbleague.server.resources.AbstractResource;
import com.yammer.dropwizard.auth.Auth;

@Path("/manager")
@Produces(MediaType.APPLICATION_JSON)
public class CreateManagerResource extends AbstractResource {

	private final ManagerManager managerManager;

	public CreateManagerResource(ManagerManager managerManager) {
		this.managerManager = managerManager;
	}

	@POST
	public Response createManager(
			@Auth Manager manager,
			@Valid CreateManagerRequest requestObject) {
		try {
			Manager toCreate = requestObject.getManager()
					.setPassword(requestObject.getPassword());
			
			Manager managerCreated = managerManager.createManager(toCreate);
			return Response.ok(managerCreated).build();
		} catch (Exception e) {
			return this.handleException(e);
		}
	}
}

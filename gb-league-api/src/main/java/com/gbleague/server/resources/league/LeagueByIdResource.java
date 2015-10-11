package com.gbleague.server.resources.league;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gbleague.manager.league.LeagueManager;
import com.gbleague.models.league.League;
import com.gbleague.models.manager.Manager;
import com.gbleague.server.resources.AbstractResource;
import com.yammer.dropwizard.auth.Auth;
import com.yammer.dropwizard.jersey.params.LongParam;

@Path("/league/{leagueId}")
@Produces(MediaType.APPLICATION_JSON)
public class LeagueByIdResource extends AbstractResource {

	private final LeagueManager leagueManager;

	public LeagueByIdResource(LeagueManager leagueManager) {
		this.leagueManager = leagueManager;
	}

	@GET
	public Response getLeague(
			@Context HttpServletRequest request,
			@Auth Manager currentManager,
			@PathParam("leagueId") LongParam leagueId) {
		try {
			League league = leagueManager.getLeagueById(leagueId.get());
			return Response.ok(league).build();
		} catch (Exception e) {
			return this.handleException(e);
		}
	}
}

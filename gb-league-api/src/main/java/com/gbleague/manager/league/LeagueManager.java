package com.gbleague.manager.league;

import com.gbleague.db.ILeagueDAO;
import com.gbleague.models.league.League;
import com.google.common.base.Optional;
import com.sun.jersey.api.NotFoundException;

public class LeagueManager {
	
	public final ILeagueDAO leagueDAO;

	public LeagueManager(ILeagueDAO leagueDAO) {
		this.leagueDAO = leagueDAO;
	}

	public League getLeagueById(Long id) {
		Optional<League> league = leagueDAO.getById(id);
		if (league.isPresent()) {
			return league.get();
		} else {
			throw new NotFoundException();
		}
	}
}

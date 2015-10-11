package com.gbleague.db;

import com.gbleague.models.league.League;
import com.google.common.base.Optional;

public interface ILeagueDAO {

	/**
	 * Get the league by the given id
	 * @param id The id of the league
	 * @return The league with the given id
	 */
	public Optional<League> getById(long id);
}

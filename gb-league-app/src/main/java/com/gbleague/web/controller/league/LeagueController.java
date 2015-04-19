package com.gbleague.web.controller.league;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbleague.league.model.league.PojoLeague;
import com.gbleague.web.dto.league.LeagueDTO;
import com.google.common.collect.Lists;

/**
 * The controller class for calls related to the league
 */
@Controller
@RequestMapping("/manager/{managerId}/league")
public class LeagueController {

	// TODO /manager/{ID}/league/{ID} GET
	// getLeagueById

	// TODO /manager/{ID}/league POST
	// createLeague

	// TODO /manager/{ID}/league/{ID} POST
	// updateLeague

	// TODO move to league controller
	@RequestMapping(value="/")
	@ResponseBody
	public List<LeagueDTO> getLeagues(Long managerId) {
		List<PojoLeague> leagues = Lists.newArrayList();
		leagues.add(new PojoLeague(1, "name1", 1));
		leagues.add(new PojoLeague(2, "name2", 1));
		leagues.add(new PojoLeague(3, "name3", 1));

		List<LeagueDTO> leagueDTOs = Lists.newArrayList();
		for (PojoLeague league : leagues) {
			// TODO move to factory object
			LeagueDTO leagueDTO = new LeagueDTO(league);
			leagueDTO.putLink("home", "/league/" + league.getId() + "/home");
			leagueDTO.putLink("my_team", "/league/" + league.getId() + "/team/my");
			leagueDTO.putLink("players", "league/" + league.getId() + "/player");
			leagueDTO.putLink("draft", "league/" + league.getId() + "/draft");
			leagueDTOs.add(leagueDTO);
		}

		return leagueDTOs;
	}
}

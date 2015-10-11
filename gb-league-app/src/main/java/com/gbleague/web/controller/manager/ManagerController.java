package com.gbleague.web.controller.manager;

import com.gbleague.league.model.IManager;
import com.gbleague.league.model.league.PojoLeague;
import com.gbleague.league.model.manager.PojoManager;
import com.gbleague.web.dto.league.LeagueDTO;
import com.gbleague.web.dto.manager.ManagerDTO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * The controller class for calls related to the manager
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

	/**
	 * Get the current manager logged in
	 */
    @RequestMapping(value="/current", method = RequestMethod.GET)
	@ResponseBody
    public ManagerDTO getCurrentManager() {
		IManager manager = new PojoManager();
		manager.setId(1);
		manager.setFirstName("Graham");
		manager.setLastName("Benevelli");

        ManagerDTO managerDTO = new ManagerDTO(manager);
		managerDTO.putLink("settings", "/manager/" + manager.getId() + "/settings");
		managerDTO.putLink("profile", "/manager/" + manager.getId() + "/profile");
		managerDTO.putLink("logout", "/manager/" + manager.getId() + "/logout");

		return managerDTO;
    }

	// TODO /manager/{ID} GET
	// getManagerById
	
	@RequestMapping(value="/", method = RequestMethod.PUT)
	@ResponseBody
	public ManagerDTO createManager(@RequestBody ManagerDTO json) {
		System.out.println(json);
		// Get data from call
		// Send to buisiness layer
		return null;
	}

	// TODO /manager/{ID} POST
	// updateManager
}
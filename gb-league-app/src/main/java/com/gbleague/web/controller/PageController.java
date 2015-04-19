package com.gbleague.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller to get the base pages
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String loadIndexPage(Model model) {
		// TODO add call to get default company
        return "redirect:/league/1/home";
    }

	@RequestMapping("/league/{leagueId}/home")
	public String loadLeagueIndex(Model model) {
		return "home";
	}

}
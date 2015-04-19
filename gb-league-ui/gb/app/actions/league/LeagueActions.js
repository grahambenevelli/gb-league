define([
	'/gb/app/dispatcher/dispatcher.js',
	'/gb/app/constants/LeagueConstants.js'
], function () {

	var Dispatcher = require('/gb/app/dispatcher/dispatcher.js');
	var LeagueConstants = require('/gb/app/constants/LeagueConstants.js');
	
	var id = 4;

	return {
		createLeague: function(name) {
			league = {
				id: id++,
				name: name
			};
			Dispatcher.dispatch({
				action: LeagueConstants.CREATE_LEAGUE,
				newLeague: league
			});
		},
		
		switchLeague: function(id) {
			Dispatcher.dispatch({
				action: LeagueConstants.SWITCH_LEAGUE,
				id: id
			});
			
		}
	}
});


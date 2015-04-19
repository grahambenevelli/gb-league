define([
	'canjs',
	'/gb/app/models/manager/manager.js',
	'/gb/app/models/league/league.js',
	'bootstrap',
	'less!/gb/app/controls/base/side_panel.less'
], function() {

	/**
	 * Dependencies from lib
	 */
	can = require('canjs');

	/**
	 * Dependencies from app
	 */
	Manager = require('/gb/app/models/manager/manager.js');
	League = require('/gb/app/models/league/league.js');

	/**
	 * Main Control
	 */
	return can.Control({
		defaults: {}
	}, {
		init: function() {
			var el = this.element;
			var self = this;
			Manager.findCurrent().done(function(manager) {
				League.findAllByManager(manager.id).done(function(leagues) {
					var currentLeague = leagues.shift();
					self.data = new can.Map({
						currentLeague: currentLeague,
						leagues: leagues,
						manager: manager
					});
					el.html(can.view('/gb/app/controls/base/side_panel.mustache', self.data))
				});
			});
		},
		userName: function() {
			return self.data.manager.firstName + " " + self.data.maanger.lastName;
		},
		".home click": function() {
			window.location = this.data.currentLeague.links.home;
		},
		".team click": function() {
			window.location = this.data.currentLeague.links.my_team;
		},
		".players click": function() {
			window.location = this.data.currentLeague.links.players;
		},
		".draft click": function() {
			window.location = this.data.currentLeague.links.draft;
		}
	});

});
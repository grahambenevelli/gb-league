define([
	'event-emitter',
	'underscore',
	'/gb/app/dispatcher/dispatcher.js',
	'/gb/app/constants/LeagueConstants.js'
], function () {

	// https://stash.spredfast.com/projects/DEV/repos/sf-webapp/pull-requests/9497/overview
	var EventEmitter = require("event-emitter");
	var Dispatcher = require('/gb/app/dispatcher/dispatcher.js');
	var LeagueConstants = require('/gb/app/constants/LeagueConstants.js');
	var _ = require("underscore");

	var CHANGE_EVENT = 'change';
	
	var _currentLeague = {
		id: 2,
		name: "Default League"
	};
	
	var _allLeagus = [{
		id: 1,
		name: "Other League 1"
	}, {
		id: 2,
		name: "Default League"
	}, {
		id: 3,
		name: "Other League 2"
	}];

	var LeagueStore = _.extend({}, EventEmitter.prototype, {
		
		getCurrentLeague: function () {
			// TODO populate with API call
			return _currentLeague;
		},
		
		getAllLeagues: function () {
			// TODO populate with API call
			return _allLeagus;
		},

		emitChange: function() {
			this.emit(CHANGE_EVENT);
		},

		/**
		 * @param {function} callback
		 */
		addChangeListener: function(callback) {
			this.on(CHANGE_EVENT, callback);
		},

		/**
		 * @param {function} callback
		 */
		removeChangeListener: function(callback) {
			this.removeListener(CHANGE_EVENT, callback);
		},

		dispatcherIndex: Dispatcher.register(function(payload) {
			console.log("dispatcherIndex");
			var action = payload.action;
			var text;
			
			switch(action) {
				case LeagueConstants.CREATE_LEAGUE:
					_allLeagus.push(payload.newLeague);
					LeagueStore.emitChange();
					break;
				case LeagueConstants.SWITCH_LEAGUE:
					var id = payload.id;
					var newLeague = _allLeagus.filter(function(league) {
						return league.id == id;
					});
					if (newLeague) {
						_currentLeague = newLeague[0];
						LeagueStore.emitChange();	
					}
					break;
			}

			return true;
		})

	});
	
	return LeagueStore;
});
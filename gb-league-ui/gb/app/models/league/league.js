define([
	'canjs',
	'underscore'
], function(){

	_ = require('underscore');
	can = require('canjs');

	League = can.Model.extend({
		create: 'POST /league',
		findOne: 'GET /league?leagueId={leagueId}',

		findAllByManager:  function( managerId ) {
			var self = this;
			return can.ajax({
				url: "/manager/" + managerId + "/league",
				type: "GET"
			}).pipe(function(data) {
				return _.each(data, function(leagueData) {
					return self.model(leagueData);
				});
			});
		}

	}, {
		// instance methods
	});

	return League;

});
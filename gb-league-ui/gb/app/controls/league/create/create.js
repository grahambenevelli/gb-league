define([
	'canjs',
	'util/UrlUtil',
	'bootstrap',
	'less!/gb/app/controls/league/create/create.less'
], function() {

//steal(
//	'can',
//	'util/UrlUtil.js',
//	'controls/league/create/create.mustache',
//	'controls/league/create/create.less',
//
//function(can, UrlUtil, leagueCreationView){

	/**
	 * Dependencies from lib
	 */
	can = require('canjs');
	UrlUtil = require('util/UrlUtil');

	return can.Control({
		defaults: {}
	}, {
		
		LEAGUE_NAME_ERROR_KEY: 'leagueNameError',
		TEAM_NAME_ERROR_KEY: 'teamNameError',
		SERVER_ERROR: 'serverError',
		
		init: function() {
			this.data = new can.Map({
				serverError: UrlUtil.getParameterByName(this.SERVER_ERROR),
				});
			this.element.html(can.view('/gb/app/controls/league/create/create.mustache', this.data));
		}, 
		
		verifyInput: function(form) {
			var leagueName = form.find('input[name="leagueName"]')[0].value;
			if (!leagueName || 0 === leagueName.length) {
				this.data.attr(this.LEAGUE_NAME_ERROR_KEY, true);
				return false;
			} else {
				this.data.attr(this.LEAGUE_NAME_ERROR_KEY, false);
			}
			
			var teamName = form.find('input[name="teamName"]')[0].value;
			if (!teamName || 0 === teamName.length) {
				this.data.attr(this.TEAM_NAME_ERROR_KEY, true);
				return false;
			} else {
				this.data.attr(this.TEAM_NAME_ERROR_KEY, false);
			}
			return true;
		},
		
		'input[type=button] click': function( input, event ) {
			var self = this;
			var form = input.parent().parent();
			
			if (this.verifyInput(form)) {
				form.submit();
			}
		}
		
	});

});
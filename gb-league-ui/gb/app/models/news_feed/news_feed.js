define([
	'canjs'
], function () {

	/**
	 * Dependencies from lib
	 */
	can = require('canjs');

	return can.Model.extend({
		findAll: 'GET /newsfeed/'
	}, {
		titleCapitalized: function() {
			return this.title.toUpperCase();
		}
	});

});
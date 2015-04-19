define([
	'canjs',
	'jquery',
	'/gb/app/models/news_feed/news_feed.js',
	'less!/gb/app/controls/home/news_feed/news_feed.less'
], function () {

	/**
	 * Dependencies
	 */
	$ = require('jquery');

	/**
	 * Dependencies from app
	 */
	NewsFeed = require('/gb/app/models/news_feed/news_feed.js');

	return can.Control({
		defaults: {}
	}, {
		init: function () {
			var el = this.element;
			var self = this;
			NewsFeed.findAll().done(function (results) {
				results[0].first = true
				self.data = new can.Map({
					feed: results
				});

				el.html(can.view('/gb/app/controls/home/news_feed/news_feed.mustache', self.data))
				$('.banner').first().removeClass('hidden')
			});
		}
	});

});
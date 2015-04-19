define([
	'canjs'
], function(){
	can = require('canjs');

	return can.Model.extend({
		create: 'POST /manager',
		findOne: 'GET /manager?managerId={managerId}',

		findCurrent:  function( params ) {
			var self = this;
			return $.get("/manager/current", {}, undefined ,"json").pipe(function(data) {
				return self.model(data);
			});
		}
	}, {
		isAdmin: function() {
			return this.attr('admin');
		},
		getName: function() {
			return this.attr('firstName') + ' ' + this.attr('lastName');
		}
	});

});
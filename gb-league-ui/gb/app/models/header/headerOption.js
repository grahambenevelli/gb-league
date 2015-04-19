steal(
	'can/model',
	'can/util',

function(Model, canUtil){

	HeaderOptions = can.Model.extend({
		findAll: 'GET /home/homeOptions.json'
	}, {
		isHome: function() {
			return this.attr('name') == 'Home';
		}
	});

	return HeaderOptions;

});
require(['/gb/app/require-main.js'], function () {
	require([
		'/gb/app/controls/league/create/create.js',
	], function () {
		var leagueCreationControl = require('/gb/app/controls/league/create/create.js');
		new leagueCreationControl('#league-creation');
	});
});
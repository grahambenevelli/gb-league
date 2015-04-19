require(['/gb/app/require-main.js'], function () {
	require([
		'/gb/app/controls/signup/signup.js'
	], function () {
		var signupControl = require('/gb/app/controls/signup/signup.js');
		new signupControl('#signup');
	});
});
require(['/gb/app/require-main.js'], function () {
	require([
		'react',
		'jsx!/gb/app/components/signin/signin',
	], function () {
		var React = require('react');
		
		var signIn = require('jsx!/gb/app/components/signin/signin');
		var mountElement = document.getElementById('signin');
		
		React.render(React.createElement(signIn), mountElement);
	});
});


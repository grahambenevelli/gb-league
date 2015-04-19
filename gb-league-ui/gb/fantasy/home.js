require(['/gb/app/require-main.js'], function () {
	require([
		'jsx!/gb/app/components/controller/main',
		'react',
	], function () {
		var React = require('react');
		var mainElement = document.getElementById('main');
		
		var main = require('jsx!/gb/app/components/controller/main');
		React.render(React.createElement(main), mainElement);
	});
});


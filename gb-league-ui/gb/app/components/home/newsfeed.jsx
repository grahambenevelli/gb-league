define([
	'react',
], function() {
	
	var React = require('react');

	var SignInPage = React.createClass({
		getInitialState: function() {
			return {};
		},
		
		render: function() {
			// TODO feed this with a backend call
			return (
				<div className="newsfeed">
					<a class="title" href="#">Watt is awesome</a>
					<div class="crop">
						<img class="image center" src="http://img.bleacherreport.net/img/images/photos/003/046/355/f0e8ebda742c2251b76c28ea9e300bdc_crop_north.jpg?w=759&h=506&q=75"/>
					</div>
					<div class="description">Nuff said</div>
				</div>
			);
		}
	});

	return SignInPage;

});
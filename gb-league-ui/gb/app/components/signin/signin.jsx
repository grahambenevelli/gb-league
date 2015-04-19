define([
	'react',
	'jquery'
], function() {
	
	var React = require('react');
	var $ = require('jquery');

	var SignInPage = React.createClass({
		getInitialState: function() {
			return {};
		},

		handleSubmit: function(e) {
			console.log("handleSubmit");
			e.preventDefault();

			var email = React.findDOMNode(this.refs.email).value.trim();
			var password = React.findDOMNode(this.refs.password).value.trim();

			// TODO verify on server
			// TODO move to url contstant thingy
			window.location.href = "/gb/fantasy/index.html";
		},
		
		render: function() {
			var error = '';
			if (this.props.error) {
				error = (
					<div className="error">
						Hello {this.props.error}
					</div>
				);
			}
			
			return (
				<form className="signinForm" onSubmit={this.handleSubmit}>
					{error}
					<input type="email" placeholder="e-mail" ref="email" />
					<input type="password" placeholder="password" ref="password" />
					<input type="submit" value="Submit" />
				</form>
			);
		}
	});
	
	return SignInPage;

});
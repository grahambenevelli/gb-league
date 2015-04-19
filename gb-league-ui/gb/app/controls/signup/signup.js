


//steal(
//	'can',
//	'util/UrlUtil.js',
//	'controls/signup/signup.mustache',
//	'models/manager/manager.js',
//	'controls/signup/signup.less',
//
//	function(can, UrlUtil, signupView, Manager){
//
//
//
//	});

define([
	'canjs',
	'util/UrlUtil',
	'/gb/app/models/manager/manager.js',
	'bootstrap',
	'less!/gb/app/controls/signup/signup.less'
], function() {

	/**
	 * Dependencies from lib
	 */
	can = require('canjs');
	UrlUtil = require('util/UrlUtil');

	/**
	 * Dependencies from app
	 */
	Manager = require('/gb/app/models/manager/manager.js');

	return can.Control({
		defaults: {}
	}, {

		FIRST_NAME_ERROR_KEY: "firstNameError",
		LAST_NAME_ERROR_KEY: "lastNameError",
		EMAIL_ERROR_KEY: "emailError",
		PASSWORD_ERROR_KEY: "passwordError",
		SERVER_ERROR: "serverError",

		init: function () {
			this.data = new can.Map({
				serverError: UrlUtil.getParameterByName(this.SERVER_ERROR)
			});
			this.element.html(can.view('/gb/app/controls/signup/signup.mustache', this.data))
		},

		verifyInput: function(form) {
			// TODO better verification use Manager for verification
			// maybe http://canjs.com/docs/can.Map.validations.html
			var firstName = form.find('input[name="firstName"]')[0].value;
			if (!firstName || 0 === firstName.length) {
				this.data.attr(this.FIRST_NAME_ERROR_KEY, true);
				return false;
			} else {
				this.data.attr(this.FIRST_NAME_ERROR_KEY, false);
			}

			var lastName = form.find('input[name="lastName"]')[0].value;
			if (!lastName || 0 === lastName.length) {
				this.data.attr(this.LAST_NAME_ERROR_KEY, true);
				return false;
			} else {
				this.data.attr(this.LAST_NAME_ERROR_KEY, false);
			}

			var email = form.find('input[name="email"]')[0].value;
			var re = /\S+@\S+\.\S+/;
			if (!re.test(email)) {
				this.data.attr(this.EMAIL_ERROR_KEY, true);
				return false;
			} else {
				this.data.attr(this.EMAIL_ERROR_KEY, false);
			}

			var password = form.find('input[name="password"]')[0].value;
			var passwordVerify = form.find('input[name="password-verify"]')[0].value;
			if (password.length < 8 || password != passwordVerify) {
				this.data.attr(this.PASSWORD_ERROR_KEY, true);
				return false;
			} else {
				this.data.attr(this.PASSWORD_ERROR_KEY, false);
			}

			return true;
		},

		'input[type=button] click': function( input, event ) {
			var self = this;
			var form = input.parent().parent();

			if (this.verifyInput(form)) {
				var manager = new Manager({
					firstName: form.find('input[name="firstName"]')[0].value,
					lastName: form.find('input[name="lastName"]')[0].value,
					email: form.find('input[name="email"]')[0].value,
					password: form.find('input[name="password"]')[0].value,
					passwordVerify: form.find('input[name="password-verify"]')[0].value
				});
				manager.save().done(function(data) {
					if (data.attr('success')) {
						document.location = data.attr('nextActions').attr('redirect');
					} else {
						self.data.attr('serverError', "Server Error Occured");
					}
				}).fail(function(data) {
					var json = JSON.parse(data.statusText);
					self.data.attr('firstNameError', json.firstNameError);
					self.data.attr('lastNameError', json.lastNameError);
					self.data.attr('emailError', json.emailError);
					self.data.attr('passwordError', json.passwordError);
					self.data.attr('serverError', json.serverError);
				});
			}
		}

	});
});
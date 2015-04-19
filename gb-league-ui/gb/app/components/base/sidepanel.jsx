define([
	'react',
	'/gb/app/actions/league/leagueActions.js',
	'less!/gb/app/components/base/sidepanel.less'
], function() {

	/**
	 * Libs 
	 */
	var React = require('react');

	/**
	 * Application Components
	 */
	var LeagueActions = require('/gb/app/actions/league/leagueActions.js');
	
	var leagueNum = 3;
	
	var LeagueOption = React.createClass({
		
		propTypes: {
			league: React.PropTypes.object.isRequired,
			onClick: React.PropTypes.func.isRequired
		},
		
		getInitialState: function() {
			return {};
		},
		
		handleClick: function() {
			this.props.onClick(this.props.league.id);
		},

		render: function() {
			var league = this.props.league;
			return (
				<li>
					<a onClick={this.handleClick}>
						<span>{league.name}</span>
					</a>
				</li>
			);
		}

	});

	return React.createClass({

		propTypes: {
			currentLeague: React.PropTypes.object.isRequired,
			allLeagues: React.PropTypes.array
		},
		
		getInitialState: function() {
			return {};
		},

		createLeagueButton: function() {
			LeagueActions.createLeague("Other League " + leagueNum++);
		},

		_switchLeague: function(id) {
			LeagueActions.switchLeague(id);
		},
		
		render: function() {
			var currentLeague = this.props.currentLeague;
			var allLeagues = this.props.allLeagues;
			var self = this;
			
			var leagueNodes = allLeagues.map(function (league) {
				if (currentLeague.id !== league.id) {
					return (
						<LeagueOption
							league={league}
							onClick={self._switchLeague}
						/>
					);
				}
			}).filter(function(n) {
				return n != undefined 
			});

			// TODO move league dropdown to own component
			return (
				<div className="nav-container">
					<div className="nav-bar">
						<ul>
							<li className="active has-sub"><a href="#"><span>{currentLeague.name}</span></a>
								<ul>
									<li className="has-sub"><a href="#"><span>Switch League</span></a>
										<ul>
										{leagueNodes}
										</ul>
									</li>
									<li>
										<a href="#">
											<span>Settings</span>
										</a>
									</li>
									<li>
										<a onClick={this.createLeagueButton}>
											<span>Create League</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="#">
									<span>Home</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span>My Team</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span>Players</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span>Draft</span>
								</a>
							</li>
						</ul>
					</div>
				</div>
			);
		}
	});

});
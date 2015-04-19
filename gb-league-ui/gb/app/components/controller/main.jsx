define([
	'jsx!/gb/app/components/base/sidepanel',
	'jsx!/gb/app/components/home/newsfeed',
	'jsx!/gb/app/components/home/standings',
	'/gb/app/stores/league/LeagueStore.js',
	'react',
], function () {
	
	/**
	 * libs 
	 */
	var React = require('react');

	/**
	* Components
	*/
	var Standings = require('jsx!/gb/app/components/home/standings');
	var SidePanel = require('jsx!/gb/app/components/base/sidepanel');
	var Newsfeed = require('jsx!/gb/app/components/home/newsfeed');

	/**
	 * Stores
	 */
	var LeagueStore = require('/gb/app/stores/league/LeagueStore.js');

	function getLeagueState() {
		return {
			currentLeague: LeagueStore.getCurrentLeague(),
			allLeagues: LeagueStore.getAllLeagues(),
		};
	}

	var MainPage = React.createClass({
		
		getInitialState: function() {
			return getLeagueState();
		},

		componentDidMount: function() {
			LeagueStore.addChangeListener(this._onChange);
		},

		componentWillUnmount: function() {
			LeagueStore.removeChangeListener(this._onChange);
		},

		render: function() {
			return (
				<div>
					<div className="left">
						<SidePanel
							currentLeague={this.state.currentLeague}
							allLeagues={this.state.allLeagues}
						/>
					</div>
					<div className="right">
						<Newsfeed />
						<Standings />
					</div>
				</div>
			);
		},

		_onChange: function() {
			this.setState({
				currentLeague: LeagueStore.getCurrentLeague(),
				allLeagues: LeagueStore.getAllLeagues(),
			});
		}
	});
	
	return MainPage;
});


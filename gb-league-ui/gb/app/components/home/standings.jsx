define([
	'react',
], function() {
	
	var React = require('react');

	var StandingsComponent = React.createClass({
		getInitialState: function() {
			return {};
		},
		
		render: function() {
			// TODO feed this with a backend call
			return (
				<div className="standings">
					<div>
						Standings
					</div>
					<ul>
						<li>
							<div>
							Team 1 10-3 600
							</div>
						</li>
						<li>
							<div>
							Team 2 9-4 600
							</div>
						</li>
						<li>
							<div>
							Team 3 5-8 600
							</div>
						</li>
						<li>
							<div>
							Team 4 2-11 600
							</div>
						</li>
					</ul>
				</div>
			);
		}
	});

	return StandingsComponent;

});
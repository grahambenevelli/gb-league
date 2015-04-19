require(['/gb/app/require-main.js'], function () {
	require(['/gb/app/controls/base/side_panel.js'], function (sidePanelControl) {
		new sidePanelControl('#side-panel');
	});
});
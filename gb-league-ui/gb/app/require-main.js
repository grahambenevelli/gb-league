require.config({
	baseUrl: '/gb/lib',
	paths: {
		"jquery": 'bower_components/jquery/dist/jquery',
		"less": 'bower_components/require-less/less',
		"lessc": 'bower_components/require-less/lessc',
		"react": 'bower_components/react/react',
		"underscore": 'bower_components/underscore/underscore',
		"JSXTransformer": "bower_components/react/JSXTransformer",
		"jsx": "bower_components/requirejs-react-jsx/jsx",
		"text": "bower_components/requirejs-text/text",
		"event-emitter": "bower_components/eventEmitter/EventEmitter",
		"react-flux": "bower_components/flux/dist/Flux",
		"normalize": "bower_components/require-less/normalize"
	},
	shim: {
		less: {
			deps:['normalize', 'lessc'],
			exports: 'less'
		},
		bootstrap: {
			deps:['jquery']
		}
	}
});
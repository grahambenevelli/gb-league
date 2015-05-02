package com.gbleague.server;

import com.example.helloworld.core.Person;
import com.example.helloworld.core.Template;
import com.example.helloworld.db.PersonDAO;
import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.resources.PeopleResource;
import com.example.helloworld.resources.PersonResource;
import com.gbleague.server.resources.signin.SigninResource;
import com.gbleague.auth.TestAuthenticator;
import com.gbleague.db.IManagerDAO;
import com.gbleague.db.file.FileManagerDAO;
import com.gbleague.manager.manager.ManagerManager;
import com.gbleague.models.manager.Manager;
import com.gbleague.server.resources.manager.CreateManagerResource;
import com.gbleague.server.resources.manager.CurrentManagerResource;
import com.gbleague.server.resources.manager.ManagerByIdResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.auth.basic.BasicAuthProvider;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.migrations.MigrationsBundle;

public class LeagueService extends Service<LeagueConfiguration> {

	public static void main(String[] args) throws Exception {
		new LeagueService().run(args);
	}

	private final HibernateBundle<LeagueConfiguration> hibernateBundle = new HibernateBundle<LeagueConfiguration>(Person.class) {

		@Override
		public DatabaseConfiguration getDatabaseConfiguration(LeagueConfiguration configuration) {
			return configuration.getDatabaseConfiguration();
		}
	};

	@Override
	public void initialize(Bootstrap<LeagueConfiguration> bootstrap) {
		bootstrap.setName("gbleague");
		initializeCommands(bootstrap);

		// Get access to static files
		bootstrap.addBundle(new AssetsBundle());

		// Hibernate related
		bootstrap.addBundle(new MigrationsBundle<LeagueConfiguration>() {

			@Override
			public DatabaseConfiguration getDatabaseConfiguration(LeagueConfiguration configuration) {
				return configuration.getDatabaseConfiguration();
			}
		});
		bootstrap.addBundle(hibernateBundle);
	}

	private void initializeCommands(Bootstrap<LeagueConfiguration> bootstrap) {
		// bootstrap.addCommand(new RenderCommand());
	}

	@Override
	public void run(LeagueConfiguration configuration, Environment environment) throws ClassNotFoundException {
		// TODO move to spring
		// DAOs
		final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory());
		final IManagerDAO managerDAO = new FileManagerDAO();

		// Managers
		final ManagerManager managerManager = new ManagerManager(managerDAO);

		// Auth
		environment.addProvider(new BasicAuthProvider<Manager>(new TestAuthenticator(managerDAO), "Enter you league password"));

		// Used for what?
		final Template template = configuration.buildTemplate();
		
		// Sessions
//		final ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//		SessionHandler sessionHandler = new SessionHandler();
//		environment.setSessionHandler(sessionHandler);

		// Healthchecks
		environment.addHealthCheck(new TemplateHealthCheck(template));

		// Test Resources
		environment.addResource(new HelloWorldResource(template));
		environment.addResource(new SigninResource());
		environment.addResource(new PeopleResource(dao));
		environment.addResource(new PersonResource(dao));
		
		// Actual resources
        environment.addResource(new ManagerByIdResource(managerManager));
		environment.addResource(new CurrentManagerResource());
		environment.addResource(new CreateManagerResource(managerManager));
	}
}

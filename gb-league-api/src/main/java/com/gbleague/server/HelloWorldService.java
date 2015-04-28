package com.gbleague.server;

import com.example.helloworld.core.Person;
import com.example.helloworld.core.Template;
import com.example.helloworld.db.PersonDAO;
import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.resources.PeopleResource;
import com.example.helloworld.resources.PersonResource;
import com.example.helloworld.resources.ProtectedResource;
import com.gbleague.auth.TestAuthenticator;
import com.gbleague.db.IManagerDAO;
import com.gbleague.db.file.FileManagerDAO;
import com.gbleague.models.manager.Manager;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.auth.basic.BasicAuthProvider;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.migrations.MigrationsBundle;

public class HelloWorldService extends Service<HelloWorldConfiguration> {
    
    public static void main(String[] args) throws Exception {
        new HelloWorldService().run(args);
    }

    private final HibernateBundle<HelloWorldConfiguration> hibernateBundle =
            new HibernateBundle<HelloWorldConfiguration>(Person.class) {
                @Override
                public DatabaseConfiguration getDatabaseConfiguration(HelloWorldConfiguration configuration) {
                    return configuration.getDatabaseConfiguration();
                }
            };

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.setName("gbleague");
        initializeCommands(bootstrap);
        
        // Get access to static files
        bootstrap.addBundle(new AssetsBundle());
        
        // Hibernate related
        bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(HelloWorldConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    private void initializeCommands(Bootstrap<HelloWorldConfiguration> bootstrap) {
//        bootstrap.addCommand(new RenderCommand());
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory());
        final IManagerDAO managerDAO = new FileManagerDAO();

        environment.addProvider(new BasicAuthProvider<Manager>(new TestAuthenticator(managerDAO),
                                                            "SUPER SECRET STUFF"));

        final Template template = configuration.buildTemplate();

        environment.addHealthCheck(new TemplateHealthCheck(template));
        
        environment.addResource(new HelloWorldResource(template));
        environment.addResource(new ProtectedResource());
        
        environment.addResource(new PeopleResource(dao));
        environment.addResource(new PersonResource(dao));
    }
}

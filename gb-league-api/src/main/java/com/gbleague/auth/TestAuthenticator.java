package com.gbleague.auth;

import com.gbleague.db.IManagerDAO;
import com.gbleague.models.manager.Manager;
import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;

public class TestAuthenticator implements Authenticator<BasicCredentials, Manager> {
    
    private IManagerDAO managerDAO;
    
    public TestAuthenticator(IManagerDAO managerDAO) {
        super();
        this.managerDAO = managerDAO;
    }
    @Override
    public Optional<Manager> authenticate(BasicCredentials credentials) throws AuthenticationException {
        Optional<Manager> manager = managerDAO.getManagerByUsername(credentials.getUsername());
        if (!manager.isPresent() || !manager.get().getPassword().equals(credentials.getPassword())) {
            return Optional.absent();
        }
        return manager;
    }
}

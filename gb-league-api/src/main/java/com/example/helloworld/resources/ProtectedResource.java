package com.example.helloworld.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gbleague.models.manager.Manager;
import com.yammer.dropwizard.auth.Auth;

@Path("/protected")
@Produces(MediaType.TEXT_PLAIN)
public class ProtectedResource {
    @GET
    public String showSecret(@Auth Manager manager) {
        return String.format("Hey there, %s. You know the secret!", manager.getUsername());
    }
}

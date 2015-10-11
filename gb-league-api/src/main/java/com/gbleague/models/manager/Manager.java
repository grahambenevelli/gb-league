package com.gbleague.models.manager;

import java.util.Objects;

import com.gbleague.models.AbstractModelWithId;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Manager extends AbstractModelWithId {
    private String username;
    
    @JsonIgnore
    private String password;

    public Manager() {}

    public Manager(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Manager setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Manager setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Manager manager = (Manager) o;

        return equals(manager);
    }

    private boolean equals(Manager manager) {
        return Objects.equals(id, manager.id)
                && Objects.equals(username, manager.username)
                && Objects.equals(password, manager.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("password", password)
                .toString();
    }

    @Override 
    public Manager setId(long id) {
        this.id = id;
        return this;
    }
}

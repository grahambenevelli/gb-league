package com.gbleague.models.manager;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class Manager {
    private long id;
    private String username;
    private String password;

    public Manager() {}

    public Manager(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public Manager setId(long id) {
        this.id = id;
        return this;
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
}
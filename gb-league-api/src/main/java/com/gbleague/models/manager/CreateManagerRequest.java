package com.gbleague.models.manager;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreateManagerRequest {
    private String username;
    private String password;

    public CreateManagerRequest() {}

    public CreateManagerRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public CreateManagerRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateManagerRequest setPassword(String password) {
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

        CreateManagerRequest manager = (CreateManagerRequest) o;

        return equals(manager);
    }

    private boolean equals(CreateManagerRequest manager) {
        return Objects.equals(username, manager.username)
                && Objects.equals(password, manager.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("password", password)
                .toString();
    }
}

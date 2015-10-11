package com.gbleague.models.manager;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreateManagerRequest {
    private Manager manager;
    private String password;

    public CreateManagerRequest() {}

    public CreateManagerRequest(Manager manager, String password) {
        this.manager = manager;
        this.password = password;
    }

    public Manager getManager() {
        return manager;
    }

    public CreateManagerRequest setManager(Manager manager) {
        this.manager = manager;
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

    private boolean equals(CreateManagerRequest request) {
        return Objects.equals(manager, request.manager)
                && Objects.equals(password, request.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manager, password);
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
                .append("manager", manager)
                .append("password", password)
                .toString();
    }
}

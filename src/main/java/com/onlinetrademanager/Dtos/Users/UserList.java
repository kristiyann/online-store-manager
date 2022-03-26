package com.onlinetrademanager.Dtos.Users;

import com.onlinetrademanager.Enums.Users.UserRole;

import java.util.UUID;

public class UserList {
    private UUID id;
    private String username;
    private String email;
    private boolean active;
    private UserRole role;

    public UserList() {
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public UserRole getRole() {
        return role;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
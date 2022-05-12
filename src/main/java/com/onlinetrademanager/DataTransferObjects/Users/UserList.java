package com.onlinetrademanager.DataTransferObjects.Users;

import com.onlinetrademanager.Enums.Users.UserRole;

import java.io.Serializable;
import java.util.UUID;

public class UserList implements Serializable {
    private UUID id;
    private String username;
    private String email;
    private boolean active;
    private UserRole role;
    private String dtype;

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

    public String getDtype() {
        return dtype;
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

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }
}
package com.onlinetrademanager.DataTransferObjects.Users;

import com.onlinetrademanager.Enums.Users.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

public class UserEdit implements Serializable {
    private UUID id;
    @NotNull
    @Size(min = 3)
    private String username;
    @NotNull
    @Email
    private String email;

    private String password;
    @NotNull
    private boolean active = true;
    @NotNull
    private UserRole role;

    public UserEdit() {
    }

    public UserEdit(UUID id, String username, String email, String password, boolean active, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

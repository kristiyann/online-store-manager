package com.onlinetrademanager.Dtos.Users;

import java.util.UUID;

public class UserPasswordEdit {
    private UUID id;
    private String password;

    public UserPasswordEdit() {
    }

    public UUID getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

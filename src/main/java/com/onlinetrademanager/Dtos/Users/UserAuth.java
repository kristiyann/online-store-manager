package com.onlinetrademanager.Dtos.Users;

public class UserAuth {
    private String email;
    private String password;

    public UserAuth() {
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
}

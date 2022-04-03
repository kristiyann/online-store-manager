package com.onlinetrademanager.Models.Users;

import com.onlinetrademanager.Enums.Users.UserRole;
import jdk.jfr.BooleanFlag;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends BaseUser implements Serializable {
    @NotNull
    @Enumerated
    private UserRole role;

    public User() { }

    public User(UUID id, String username, String email, String password, UserRole role) {
        super(id, username, email, password);
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", role=" + role +
                '}';
    }
}

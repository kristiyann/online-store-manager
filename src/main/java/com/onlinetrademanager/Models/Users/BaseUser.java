package com.onlinetrademanager.Models.Users;

import com.onlinetrademanager.Enums.Users.SiteTheme;
import com.onlinetrademanager.Enums.Users.UserRole;
import jdk.jfr.BooleanFlag;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)", updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    @Size(min= 3)
    @Column(unique = true)
    private String username;

    @NotNull
    @Email
    @Column(unique = true) // Constraint so that there is only 1 user with said email
    private String email;

    @NotNull
    private String password;

    @BooleanFlag
    @NotNull
    private boolean active = true;

    @Column(updatable = false, insertable = false)
    private String dtype;

    private SiteTheme preferredUserTheme;

    @NotNull
    @Enumerated
    private UserRole role;

    public BaseUser() { }

    public BaseUser(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public SiteTheme getPreferredUserTheme() {
        return preferredUserTheme;
    }

    public void setPreferredUserTheme(SiteTheme prefferedUserTheme) {
        this.preferredUserTheme = prefferedUserTheme;
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
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

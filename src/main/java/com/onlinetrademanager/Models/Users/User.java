package com.onlinetrademanager.Models.Users;

import com.onlinetrademanager.Enums.Users.UserRole;
import com.onlinetrademanager.Models.Store;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends BaseUser implements Serializable {
    @NotNull
    @Enumerated
    private UserRole role;

    @OneToMany
    private Set<Store> stores = new HashSet<>();

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

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role=" + role +
                '}';
    }
}

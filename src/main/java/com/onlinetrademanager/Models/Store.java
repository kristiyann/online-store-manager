package com.onlinetrademanager.Models;


import com.onlinetrademanager.Models.Users.User;
import jdk.jfr.BooleanFlag;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Store {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    private String address;

    @BooleanFlag
    private boolean active = true;

    @ManyToOne
    @NotNull
    private User user;

    @OneToMany
    private Set<Item> items = new HashSet<>();

    public Store(){
        setActive(true);
    }

    public Store(Store store){
        setAddress(store.getAddress());
        setActive(store.getActive());
    }

    public Store(String address, boolean storeActive, Set<Item> items){
        setAddress(address);
        setActive(storeActive);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean storeActive) {
        this.active = storeActive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.onlinetrademanager.Models;


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
    private boolean storeActive;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Item> items;

    public Store(){
        setAddress("Test Address");
        setStoreActive(true);
        setItems(new HashSet<Item>());
    }

    public Store(Store store){
        setAddress(store.getAddress());
        setStoreActive(store.getStoreActive());
        setItems(store.getItems());
    }

    public Store(String address, boolean storeActive, Set<Item> items){
        setAddress(address);
        setStoreActive(storeActive);
        setItems(items);
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

    public boolean getStoreActive() {
        return storeActive;
    }

    public void setStoreActive(boolean storeActive) {
        this.storeActive = storeActive;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}

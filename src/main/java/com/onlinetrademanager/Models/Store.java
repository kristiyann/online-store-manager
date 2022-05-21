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


    public Store(){
        setAddress("Test Address");
        setStoreActive(true);
    }

    public Store(Store store){
        setAddress(store.getAddress());
        setStoreActive(store.getStoreActive());
    }

    public Store(String address, boolean storeActive, Set<Item> items){
        setAddress(address);
        setStoreActive(storeActive);
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

}

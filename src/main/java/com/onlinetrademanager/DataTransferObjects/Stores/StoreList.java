package com.onlinetrademanager.DataTransferObjects.Stores;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class StoreList implements Serializable {
    private UUID id;
    private String address;
    private boolean active = true;
    private UUID userId;

    public StoreList() {
    }

    public StoreList(UUID id, String address, boolean active) {
        this.id = id;
        this.address = address;
        this.active = active;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "StoreList{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", active=" + active +
                ", userId=" + userId +
                '}';
    }
}

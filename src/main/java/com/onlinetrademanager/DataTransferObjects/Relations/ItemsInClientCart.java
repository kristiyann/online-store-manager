package com.onlinetrademanager.DataTransferObjects.Relations;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ItemsInClientCart {
    @NotNull
    private UUID clientId;
    @NotNull
    private UUID itemId;

    boolean insert;

    public ItemsInClientCart() {
    }

    public UUID getClientId() {
        return clientId;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }
}

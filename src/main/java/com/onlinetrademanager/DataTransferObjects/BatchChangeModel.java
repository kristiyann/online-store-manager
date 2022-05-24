package com.onlinetrademanager.DataTransferObjects;

import java.util.List;
import java.util.UUID;

public class BatchChangeModel {
    private UUID objectId;
    private List<UUID> itemIds;
    private boolean insert = true;

    public BatchChangeModel() {
    }

    public BatchChangeModel(UUID objectId, List<UUID> itemIds, boolean insert) {
        this.objectId = objectId;
        this.itemIds = itemIds;
        this.insert = insert;
    }

    public UUID getObjectId() {
        return objectId;
    }

    public List<UUID> getItemIds() {
        return itemIds;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    public void setItemIds(List<UUID> itemIds) {
        this.itemIds = itemIds;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }
}

package com.onlinetrademanager.DataTransferObjects.XRefs;

import com.onlinetrademanager.Models.XRefOrdersItems;

import java.util.UUID;

public class XRefOrdersItemsList {
    private UUID xRefId;

    private UUID itemId;

    int itemQuantity;

    public XRefOrdersItemsList() {}

    public XRefOrdersItemsList(UUID xRefId, UUID itemId, int itemQuantity) {
        this.xRefId = xRefId;
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
    }

    public UUID getxRefId() {
        return xRefId;
    }

    public UUID getItemId() {
        return itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setxRefId(UUID xRefId) {
        this.xRefId = xRefId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}

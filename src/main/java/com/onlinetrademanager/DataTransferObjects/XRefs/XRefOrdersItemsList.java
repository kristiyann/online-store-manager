package com.onlinetrademanager.DataTransferObjects.XRefs;

import com.onlinetrademanager.Models.XRefOrdersItems;

import java.math.BigDecimal;
import java.util.UUID;

public class XRefOrdersItemsList {
    private UUID xRefId;

    private UUID itemId;

    private int itemQuantity;

    private String name;

    private BigDecimal price;

    private String firstImgUrl;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setFirstImgUrl(String firstImgUrl) {
        this.firstImgUrl = firstImgUrl;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getFirstImgUrl() {
        return firstImgUrl;
    }
}

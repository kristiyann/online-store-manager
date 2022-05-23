package com.onlinetrademanager.DataTransferObjects.XRefs;

import com.onlinetrademanager.DataTransferObjects.Items.ItemList;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Models.XRefClientsItems;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public class XRefClientsItemsList {
    private UUID xRefId;
    private ItemList item;
    int itemQuantity;
    LocalDate createDate;

    public XRefClientsItemsList(UUID xRefId, ItemList item, int itemQuantity, LocalDate createDate) {
        this.xRefId = xRefId;
        this.item = item;
        this.itemQuantity = itemQuantity;
        this.createDate = createDate;
    }

    public XRefClientsItemsList() {}

    public UUID getxRefId() {
        return xRefId;
    }

    public ItemList getItem() {
        return item;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setxRefId(UUID xRefId) {
        this.xRefId = xRefId;
    }

    public void setItem(ItemList item) {
        this.item = item;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}

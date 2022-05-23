package com.onlinetrademanager.Models;

import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Models.Users.Client;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class XRefClientsItems {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    int itemQuantity;

    LocalDate createDate;

    public XRefClientsItems() {
    }

    public XRefClientsItems(UUID id, Client client, Item item, int itemQuantity) {
        this.id = id;
        this.client = client;
        this.item = item;
        this.itemQuantity = itemQuantity;
    }

    public UUID getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Item getItem() {
        return item;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}

package com.onlinetrademanager.Models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class XRefOrdersItems {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Item item;

    int itemQuantity;

    public XRefOrdersItems(UUID id, Order order, Item item, int itemQuantity) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.itemQuantity = itemQuantity;
    }

    public XRefOrdersItems() {}

    public UUID getId() {
        return id;
    }

    public Order getOrder() {
        return order;
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

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}

package com.onlinetrademanager.Models;
import com.fasterxml.jackson.annotation.*;
import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Models.Users.Client;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Enumerated
    private ItemCategory category;

    @NotNull
    @Positive
    private BigDecimal price;

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private final LocalDate createDate = LocalDate.now();

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate changeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @Nullable
    private Sale sale;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @OneToMany
    @JoinColumn(name = "item_id")
    private Set<Image> images = new HashSet<>();

//    @ManyToMany
//    @JsonIgnore
//    private Set<Client> ClientCarts = new HashSet<>();

    @OneToMany
    private Set<XRefClientsItems> clientCarts = new HashSet<>();

    @OneToMany
    private Set<XRefOrdersItems> orders = new HashSet<>();


    public Item() {
    }

    public Item(Item item){
        setTitle(item.getTitle());
        setDescription(item.getDescription());
        setCategory(item.getCategory());
        setPrice(item.getPrice());
        setStore(item.getStore());
        setSale(item.getSale());
        // setCreateDate(item.getCreateDate());
        setChangeDate(item.getChangeDate());
    }

    public Item(String title, String description, ItemCategory itemCategory,
                BigDecimal itemPrice, Store store, Sale sale){
        // confirm how sale will be used when creating prods
        // independent sales, which will be assigned to items
        setTitle(title);
        setDescription(description);
        setCategory(itemCategory);
        setPrice(itemPrice);
        // setCreateDate(LocalDate.now());
        setStore(store);
        setSale(sale);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String itemTitle) {
        this.title = itemTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String itemDescription) {
        this.description = itemDescription;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory itemCategory) {
        this.category = itemCategory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal itemPrice) {
        this.price = itemPrice;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

//    public void setCreateDate(LocalDate itemCreateDate) {
//        this.createDate = itemCreateDate;
//    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate itemChangeDate) {
        this.changeDate = itemChangeDate;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<XRefClientsItems> getClientCarts() {
        return clientCarts;
    }

    public void setClientCarts(Set<XRefClientsItems> clientCarts) {
        this.clientCarts = clientCarts;
    }

    public Set<XRefOrdersItems> getOrders() {
        return orders;
    }

    public void setOrders(Set<XRefOrdersItems> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Item[ " + getId() + " ]: " +
                "Title: " + getTitle() +
                "Description: " + getDescription() +
                "Category: " + getCategory() +
                "Price: " + getPrice() +
                "CreationDate: " + getCreateDate() +
                "ChangeDate: " + getChangeDate() +
                "ID Sale: " + getSale().toString() +
                "ID Store: " + getStore().toString() +
                "Title: " + getTitle();

    }
}

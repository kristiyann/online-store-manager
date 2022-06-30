package com.onlinetrademanager.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.onlinetrademanager.Enums.Item.ItemCategory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    @Size(max = 5000)
    private String description;

    @NotNull
    @Enumerated
    private ItemCategory category;

    @NotNull
    @Positive
    private BigDecimal price;

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createDate;

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime changeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @Nullable
    private Sale sale;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Store store;

    @OneToMany
    @JoinColumn(name = "item_id")
    private Set<Image> images = new HashSet<>();

    @OneToMany
    private Set<XRefClientsItems> clientCarts = new HashSet<>();

    @OneToMany(mappedBy = "item")
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime itemChangeDate) {
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

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        assert getSale() != null;
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

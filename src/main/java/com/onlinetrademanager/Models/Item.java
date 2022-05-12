package com.onlinetrademanager.Models;
import com.fasterxml.jackson.annotation.*;
import com.onlinetrademanager.Enums.Item.ItemCategory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private String itemTitle;

    @NotNull
    private String itemDescription;

    @NotNull
    @Enumerated
    private ItemCategory itemCategory;

    @NotNull
    @Positive
    private BigDecimal itemPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate itemCreateDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate itemChangeDate;

    // one to one
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Sale sale;

    // one to one
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Store store;

    // Relations

    @JsonIgnore
    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Image> imageSet;

    public Item() {
    }

    public Item(Item item){
        setItemTitle(item.getItemTitle());
        setItemDescription(item.getItemDescription());
        setItemCategory(item.getItemCategory());
        setItemPrice(item.getItemPrice());
        setStore(item.getStore());
        setSale(item.getSale());
        setItemCreateDate(item.getItemCreateDate());
        setItemChangeDate(item.getItemChangeDate());
        setImageSet(item.getImageSet());
    }

    public Item(String itemTitle, String itemDescription, ItemCategory itemCategory,
                BigDecimal itemPrice, Store store, Sale sale){
        // confirm how sale will be used when creating prods
        // independent sales, which will be assigned to items
        setItemTitle(itemTitle);
        setItemDescription(itemDescription);
        setItemCategory(itemCategory);
        setItemPrice(itemPrice);
        setItemCreateDate(LocalDate.now());
        setStore(store);
        setSale(sale);
        imageSet = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public LocalDate getItemCreateDate() {
        return itemCreateDate;
    }

    public void setItemCreateDate(LocalDate itemCreateDate) {
        this.itemCreateDate = itemCreateDate;
    }

    public LocalDate getItemChangeDate() {
        return itemChangeDate;
    }

    public void setItemChangeDate(LocalDate itemChangeDate) {
        this.itemChangeDate = itemChangeDate;
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

    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }

    @Override
    public String toString() {
        return "Item[ " + getId() + " ]: " +
                "Title: " + getItemTitle() +
                "Description: " + getItemDescription() +
                "Category: " + getItemCategory() +
                "Price: " + getItemPrice() +
                "CreationDate: " + getItemCreateDate() +
                "ChangeDate: " + getItemChangeDate() +
                "ID Sale: " + getSale().toString() +
                "ID Store: " + getStore().toString() +
                "Title: " + getItemTitle();

    }
}

package com.onlinetrademanager.DataTransferObjects.Items;

import com.onlinetrademanager.Enums.Item.ItemCategory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ItemEdit implements Serializable {
    private UUID id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private ItemCategory category;
    @NotNull
    @Positive
    private BigDecimal price;

    private UUID storeId;

    private UUID saleId;

    private Set<String> imageUrls = new HashSet<>();

    public ItemEdit() {
    }

    public ItemEdit(UUID id, String title, String description, ItemCategory category, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public UUID getSaleId() {
        return saleId;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public void setSaleId(UUID saleId) {
        this.saleId = saleId;
    }

    public void setStoreId(UUID storeId) {
        this.storeId = storeId;
    }

    public Set<String> getImageUrls() {
        return imageUrls;
    }

    @Override
    public String toString() {
        return "ItemEdit{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", storeId=" + storeId +
                ", saleId=" + saleId +
                ", imageUrls=" + imageUrls +
                '}';
    }
}

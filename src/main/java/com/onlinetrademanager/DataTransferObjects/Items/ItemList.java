package com.onlinetrademanager.DataTransferObjects.Items;

import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Models.Image;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemList implements Serializable {
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
    private BigDecimal originalPrice;
    private LocalDate createDate;
    private LocalDate changeDate;
    private List<String> images = new ArrayList<>();
    private UUID storeId;

    public ItemList() {
    }

    public ItemList(UUID id, String title, String description, ItemCategory category, BigDecimal price, LocalDate createDate, LocalDate changeDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.createDate = createDate;
        this.changeDate = changeDate;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public void setStoreId(UUID storeId) {
        this.storeId = storeId;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public List<String> getImages() {
        return images;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }
}

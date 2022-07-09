package com.onlinetrademanager.DataTransferObjects.Items;

import com.onlinetrademanager.DataTransferObjects.GenericComboBox;
import com.onlinetrademanager.Enums.Item.ItemCategory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private LocalDateTime createDate;
    private LocalDateTime changeDate;
    private List<String> images = new ArrayList<>();
    private GenericComboBox store;
    private double salePercentage;
    private UUID saleId;

    public ItemList() {
    }

    public ItemList(UUID id, String title, String description, ItemCategory category, BigDecimal price, LocalDateTime createDate, LocalDateTime changeDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.createDate = createDate;
        this.changeDate = changeDate;
    }

    public UUID getSaleId() {
        return saleId;
    }

    public void setSaleId(UUID saleId) {
        this.saleId = saleId;
    }

    public UUID getId() {
        return id;
    }

    public double getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(double salePercentage) {
        this.salePercentage = salePercentage;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

    public void setStore(GenericComboBox store) {
        this.store = store;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public GenericComboBox getStore() {
        return store;
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

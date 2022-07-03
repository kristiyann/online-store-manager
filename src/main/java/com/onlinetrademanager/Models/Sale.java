package com.onlinetrademanager.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jdk.jfr.BooleanFlag;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sale implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate saleStartDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate saleEndDate;

    @NotNull
    private BigDecimal salePercentage;

    @NotNull
    @BooleanFlag
    private boolean deleted;

    // Relations

    @NotNull
    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public Sale() {
    }

    public Sale(Sale sale){
        setSaleStartDate(sale.getSaleStartDate());
        setSaleEndDate(sale.getSaleEndDate());
        setSalePercentage(sale.getSalePercentage());
        setDeleted(sale.isDeleted());
        setItem(sale.getItem());
    }

    public Sale(LocalDate saleStartDate, LocalDate saleEndDate, BigDecimal salePercentage){
        setSaleStartDate(saleStartDate);
        setSaleEndDate(saleEndDate);
        setSalePercentage(salePercentage);
        setDeleted(false);
        item = new Item();
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(LocalDate saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public LocalDate getSaleEndDate() {
        return saleEndDate;
    }

    public void setSaleEndDate(LocalDate saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(BigDecimal salePercentage) {
        this.salePercentage = salePercentage;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

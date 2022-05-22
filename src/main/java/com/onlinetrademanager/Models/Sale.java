package com.onlinetrademanager.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class Sale implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @NotNull
    private BigDecimal salePercentage;

    // Relations

    // @NotNull
    @OneToMany
    @JoinColumn(name = "sale", nullable = false)
    private Set<Item> items = new HashSet<>();

    public Sale() {
    }

    public Sale(Sale sale){
        setStartDate(sale.getStartDate());
        setEndDate(sale.getEndDate());
    }

    public Sale(LocalDate startDate, LocalDate saleEndDate, BigDecimal salePercentage){
        setStartDate(startDate);
        setEndDate(saleEndDate);
        setSalePercentage(salePercentage);
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setStartDate(LocalDate saleStartDate) {
        this.startDate = saleStartDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate saleEndDate) {
        this.endDate = saleEndDate;
    }

    public BigDecimal getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(BigDecimal salePercentage) {
        this.salePercentage = salePercentage;
    }

    public void setItems(HashSet<Item> items) {
        this.items = items;
    }
}

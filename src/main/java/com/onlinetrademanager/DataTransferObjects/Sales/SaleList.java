package com.onlinetrademanager.DataTransferObjects.Sales;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class SaleList implements Serializable {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    @NotNull
    private double salePercentage;

    public SaleList() {
    }

    public SaleList(UUID id, LocalDate startDate, LocalDate endDate, double salePercentage) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salePercentage = salePercentage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(double salePercentage) {
        this.salePercentage = salePercentage;
    }
}

package com.onlinetrademanager.Dtos.DeliveryCompanies;

import java.math.BigDecimal;
import java.util.UUID;

public class DeliveryCompanyEdit {
    private UUID id;

    private String name;

    private BigDecimal deliveryFee;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}

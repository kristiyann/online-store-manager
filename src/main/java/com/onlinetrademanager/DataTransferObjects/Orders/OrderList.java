package com.onlinetrademanager.DataTransferObjects.Orders;

import com.onlinetrademanager.DataTransferObjects.GenericComboBox;
import com.onlinetrademanager.DataTransferObjects.XRefs.XRefOrdersItemsList;
import com.onlinetrademanager.Enums.Order.OrderStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class OrderList implements Serializable {
    private UUID id;
    @NotNull
    private BigDecimal totalPrice;
    @NotNull
    private OrderStatus status;
    private LocalDate changeDate;
    private LocalDate createDate;

    private GenericComboBox deliveryCompany;
    private UUID userId;
    private String deliveryAddress;

    private Set<XRefOrdersItemsList> items = new HashSet<>();

    public OrderList() {}

    public OrderList(UUID id, BigDecimal totalPrice, OrderStatus status, LocalDate changeDate) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.changeDate = changeDate;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setDeliveryCompany(GenericComboBox deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public GenericComboBox getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<XRefOrdersItemsList> getItems() {
        return items;
    }

    public void setItems(Set<XRefOrdersItemsList> items) {
        this.items = items;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderList entity = (OrderList) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.totalPrice, entity.totalPrice) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.changeDate, entity.changeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, status, changeDate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "totalPrice = " + totalPrice + ", " +
                "status = " + status + ", " +
                "changeDate = " + changeDate + ")";
    }
}

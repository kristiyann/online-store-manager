package com.onlinetrademanager.DataTransferObjects.Orders;

import com.onlinetrademanager.Enums.Order.OrderStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderEdit implements Serializable {
    private UUID id;
    @NotNull
    private OrderStatus status;

    private String deliveryAddress;

    // private List<UUID> itemIds;

    private UUID deliveryCompanyId;

    public OrderEdit(UUID id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

//    public List<UUID> getItemIds() {
//        return itemIds;
//    }
//
//    public void setItemIds(List<UUID> itemIds) {
//        this.itemIds = itemIds;
//    }

    public void setDeliveryCompanyId(UUID deliveryCompanyId) {
        this.deliveryCompanyId = deliveryCompanyId;
    }

    public UUID getDeliveryCompanyId() {
        return deliveryCompanyId;
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
        OrderEdit entity = (OrderEdit) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "status = " + status + ")";
    }
}

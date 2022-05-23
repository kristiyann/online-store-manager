package com.onlinetrademanager.DataTransferObjects.Orders;

import com.onlinetrademanager.Enums.Order.OrderStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderInsert implements Serializable {
    private final UUID id;

    private List<UUID> itemIds = new ArrayList<>();

    private UUID deliveryCompanyId;

    private UUID userId;

    public OrderInsert(UUID id) {
        this.id = id;
    }

    public OrderInsert(UUID id, List<UUID> itemIds, UUID deliveryCompanyId, UUID userId) {
        this.id = id;
        this.itemIds = itemIds;
        this.deliveryCompanyId = deliveryCompanyId;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public List<UUID> getItemIds() {
        return itemIds;
    }

    public UUID getDeliveryCompanyId() {
        return deliveryCompanyId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setDeliveryCompanyId(UUID deliveryCompanyId) {
        this.deliveryCompanyId = deliveryCompanyId;
    }

    public void setItemIds(List<UUID> itemIds) {
        this.itemIds = itemIds;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", ";
    }
}

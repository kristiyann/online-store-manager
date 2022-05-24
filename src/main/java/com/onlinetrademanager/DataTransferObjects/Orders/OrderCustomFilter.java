package com.onlinetrademanager.DataTransferObjects.Orders;

import com.onlinetrademanager.Enums.Order.OrderStatus;

public class OrderCustomFilter {
    private OrderStatus status;

    public OrderCustomFilter() {}

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

package com.onlinetrademanager.Models;


import com.fasterxml.jackson.annotation.*;
import com.onlinetrademanager.Enums.Order.OrderStatus;
import com.onlinetrademanager.Models.Users.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="Order_User")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    private BigDecimal totalPrice;

    @NotNull
    @Enumerated
    private OrderStatus orderStatus;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderCreateDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderChangeDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryCompany deliveryCompany;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    public Order() {

    }

    public Order(Order order) {
        setTotalPrice(order.getTotalPrice());
        setOrderStatus(order.getOrderStatus());
        setOrderCreateDate(order.getOrderCreateDate());
        setOrderChangeDate(order.getOrderChangeDate());
        setDeliveryCompany(order.getDeliveryCompany());
        setUser(order.getUser());
        setStore(order.getStore());

    }

    public Order(BigDecimal totalPrice, OrderStatus orderStatus, DeliveryCompany deliveryCompany,
                 User user, Store store) {
        setTotalPrice(totalPrice);
        setOrderStatus(orderStatus);
        setOrderCreateDate(LocalDate.now());
        setOrderChangeDate(LocalDate.now());
        setDeliveryCompany(deliveryCompany);
        setUser(user);
        setStore(store);

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(LocalDate orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public LocalDate getOrderChangeDate() {
        return orderChangeDate;
    }

    public void setOrderChangeDate(LocalDate orderChangeDate) {
        this.orderChangeDate = orderChangeDate;
    }

    public DeliveryCompany getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(DeliveryCompany deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}

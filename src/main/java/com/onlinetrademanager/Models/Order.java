package com.onlinetrademanager.Models;


import com.onlinetrademanager.Enums.Order.OrderStatus;
import com.onlinetrademanager.Models.Users.Client;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "order_user")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

//    @NotNull
//    private BigDecimal totalPrice;

    @NotNull
    @Enumerated
    private OrderStatus status;

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private final LocalDate createDate = LocalDate.now();

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate changeDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private DeliveryCompany deliveryCompany;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Client client;

//    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
//    private Store store;

    @Size(max = 5000)
    private String deliveryAddress;

    @OneToMany(mappedBy = "order")
    private Set<XRefOrdersItems> items = new HashSet<>();

    public Order() {
    }

    public Order(UUID id, BigDecimal totalPrice, OrderStatus status, LocalDate changeDate, DeliveryCompany deliveryCompany, Client client,
//                 Store store,
                 Set<XRefOrdersItems> items) {
        this.id = id;
//        this.totalPrice = totalPrice;
        this.status = status;
        this.changeDate = changeDate;
        this.deliveryCompany = deliveryCompany;
        this.client = client;
//        this.store = store;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

//    public BigDecimal getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

//    public void setCreateDate(LocalDate orderCreateDate) {
//        this.createDate = orderCreateDate;
//    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate orderChangeDate) {
        this.changeDate = orderChangeDate;
    }

    public DeliveryCompany getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(DeliveryCompany deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

//    public Store getStore() {
//        return store;
//    }

//    public void setStore(Store store) {
//        this.store = store;
//    }

    public Set<XRefOrdersItems> getItems() {
        return items;
    }

    public void setItems(Set<XRefOrdersItems> items) {
        this.items = items;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}

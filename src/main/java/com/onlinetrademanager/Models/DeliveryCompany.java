package com.onlinetrademanager.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class DeliveryCompany implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal deliveryFee;

    /** Relations **/

    // @JsonIgnore
    // private HashSet<Order> orders;


    public DeliveryCompany() {
    }

    public DeliveryCompany(UUID id, String name, BigDecimal deliveryFee) {
        this.id = id;
        this.name = name;
        this.deliveryFee = deliveryFee;
    }

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

    @Override
    public String toString() {
        return "DeliveryCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryFee='" + deliveryFee + '\'' +
                '}';
    }
}

package com.onlinetrademanager.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinetrademanager.Models.Users.Client;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class BankAccount implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    private String bankName;

    @CreditCardNumber
    private String number;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private Client client;

    public BankAccount() {
    }

    public BankAccount(UUID id, String bankName, String number, Client client) {
        this.id = id;
        this.bankName = bankName;
        this.number = number;
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String cardNumber) {
        this.number = cardNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", cardNumber='" + number + '\'' +
                ", client=" + client.getId() +
                '}';
    }
}

package com.onlinetrademanager.DataTransferObjects.BankAccounts;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class BankAccountEdit implements Serializable {
    private UUID id;
    @NotNull
    private String bankName;
    @CreditCardNumber
    private String cardNumber;
    private String CVV;
    private LocalDate expiryDate;

    public BankAccountEdit() {
    }

    public BankAccountEdit(UUID id, String bankName, String cardNumber, String CVV, LocalDate expiryDate) {
        this.id = id;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.expiryDate = expiryDate;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}

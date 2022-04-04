package com.onlinetrademanager.DataTransferObjects.BankAccounts;

import java.time.LocalDate;
import java.util.UUID;

public class BankAccountInsert {
    private String bankName;

    private String cardNumber;

    private String CVV;

    private LocalDate expiryDate;

    private UUID clientId;

    public BankAccountInsert() {
    }

    public String getBankName() {
        return bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}

package com.onlinetrademanager.DataTransferObjects.BankAccounts;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

// For some reason using this DTO to return objects only gives us the expiry date field. For now, we will keep using the DbObj.

public class BankAccountList implements Serializable {
    private UUID id;

    private String bankName;

    private String cardNumber;

    private String CVV;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate expiryDate;

    public BankAccountList() {}

    public void setId(UUID id) {
        this.id = id;
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
}

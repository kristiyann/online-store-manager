package com.onlinetrademanager.DataTransferObjects.BankAccounts;

import java.util.UUID;

public class BankAccountInsert {
    private String bankName;

    private String number;

    private UUID clientId;

    public BankAccountInsert() {
    }

    public String getBankName() {
        return bankName;
    }

    public String getNumber() {
        return number;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}

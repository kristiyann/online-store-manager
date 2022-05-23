package com.onlinetrademanager.DataTransferObjects.Clients;

import com.onlinetrademanager.DataTransferObjects.BankAccounts.BankAccountList;
import com.onlinetrademanager.DataTransferObjects.Items.ItemList;
import com.onlinetrademanager.DataTransferObjects.XRefs.XRefClientsItemsList;
import com.onlinetrademanager.Enums.Users.UserRole;
import com.onlinetrademanager.Models.BankAccount;
import com.onlinetrademanager.Models.Item;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClientList implements Serializable {
    private UUID id;
    private String username;
    private String email;
    // private boolean active;
    private String dtype;
    //private Set<BankAccountList> bankAccounts = new HashSet<>();
    private Set<XRefClientsItemsList> cart;
    // private HashSet<Order> purchaseHistory;

    public ClientList() {
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

//    public boolean isActive() {
//        return active;
//    }

    public String getDtype() {
        return dtype;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCart(Set<XRefClientsItemsList> cart) {
        this.cart = cart;
    }

    public Set<XRefClientsItemsList> getCart() {
        return cart;
    }

    //    public void setActive(boolean active) {
//        this.active = active;
//    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

//    public void addBankAccount(BankAccountList bankAccount) {
//        bankAccounts.add(bankAccount);
//    }
//
//    public Set<BankAccountList> getBankAccounts() {
//        return bankAccounts;
//    }
}

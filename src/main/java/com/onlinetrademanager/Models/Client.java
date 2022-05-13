package com.onlinetrademanager.Models;

import com.onlinetrademanager.Enums.Users.UserRole;
import com.onlinetrademanager.Models.Users.User;

import java.io.Serializable;
import java.util.UUID;

public class Client extends User implements Serializable {
    // private HashSet<BankAccount> bankAccounts;
    // private HashSet<Item> cart;
    // private HashSet<Order> purchaseHistory;


    public Client() {
    }

    public Client(UUID id, String username, String email, String password, UserRole role) {
        super(id, username, email, password, role);
    }
}

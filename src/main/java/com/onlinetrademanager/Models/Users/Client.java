package com.onlinetrademanager.Models.Users;

import com.onlinetrademanager.Models.BankAccount;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Client extends BaseUser implements Serializable {
    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<BankAccount> bankAccounts = new HashSet<>();

    // private Set<Item> cart;
    // private Set<Order> purchaseHistory;

    public Client() {
    }

    public Client(UUID id, String username, String email, String password) {
        super(id, username, email, password);
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}

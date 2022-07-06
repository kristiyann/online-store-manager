package com.onlinetrademanager.Models.Users;

import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Models.BankAccount;
import com.onlinetrademanager.Models.ClientBlockedCategoriesOnFeed;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Models.XRefClientsItems;

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

    @OneToMany
    private Set<XRefClientsItems> cart = new HashSet<>();

    @OneToMany
    private Set<Order> orders;

    @OneToMany
    private Set<ClientBlockedCategoriesOnFeed> blockedCategoriesOnFeed;

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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<XRefClientsItems> getCart() {
        return cart;
    }

    public void setCart(Set<XRefClientsItems> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}

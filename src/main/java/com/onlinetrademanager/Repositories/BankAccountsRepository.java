package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.BankAccount;
import com.onlinetrademanager.Models.Users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BankAccountsRepository extends JpaRepository<BankAccount, UUID> {
    // List<BankAccount> findAllByClients(UUID clientId);

    List<BankAccount> findAllBankAccountsByClient(Client client);

    void deleteBankAccountById(UUID id);
}

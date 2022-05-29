package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.BankAccount;
import com.onlinetrademanager.Models.Users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BankAccountsRepository extends JpaRepository<BankAccount, UUID> {
    List<BankAccount> findAllBankAccountsByClient(Client client);

    void deleteBankAccountById(UUID id);
}

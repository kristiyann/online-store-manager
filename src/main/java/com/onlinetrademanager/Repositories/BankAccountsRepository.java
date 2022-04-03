package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BankAccountsRepository extends JpaRepository<BankAccount, UUID> {
    List<BankAccount> findAllByClient(UUID clientId);
}

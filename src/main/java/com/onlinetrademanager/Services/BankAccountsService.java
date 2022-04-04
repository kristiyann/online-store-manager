package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.BankAccounts.BankAccountInsert;
import com.onlinetrademanager.DataTransferObjects.BankAccounts.BankAccountList;
import com.onlinetrademanager.Models.BankAccount;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Repositories.BankAccountsRepository;
import com.onlinetrademanager.Repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Services are a layer in the MVC design architecture which is used for building this application.
 * They describe the behaviour of models.
 * The current service is used to give functionality to objects of type Bank Account.
 * **/

@Service
@Transactional
public class BankAccountsService {
    private final BankAccountsRepository bankAccountsRepository;
    private final ClientsRepository clientsRepository;

    @Autowired
    public BankAccountsService(BankAccountsRepository bankAccountsRepository, ClientsRepository clientsRepository) {
        this.bankAccountsRepository = bankAccountsRepository;
        this.clientsRepository = clientsRepository;
    }

    public UUID insertBankAccount(BankAccountInsert toInsert) {
        BankAccount bankAccount = convertInsertModelToDbObj(toInsert);
        bankAccountsRepository.save(bankAccount);
        return bankAccount.getId();
    }

    public List<BankAccount> findAllByClient(UUID clientId) {
        Client client = clientsRepository.getById(clientId);

        return bankAccountsRepository.findAllBankAccountsByClient(client);
//                .stream()
//                .map(this::convertDbObjToBankAccountList)
//                .collect(Collectors.toList());
    }

    /** region Converter methods **/

    private BankAccount convertInsertModelToDbObj(BankAccountInsert bankAccountInsert) {
        BankAccount bankAccount = new BankAccount();

        bankAccount.setBankName(bankAccountInsert.getBankName());
        bankAccount.setCardNumber(bankAccountInsert.getCardNumber());
        bankAccount.setCVV(bankAccountInsert.getCVV());
        bankAccount.setExpiryDate(bankAccountInsert.getExpiryDate());

        Client client = clientsRepository.getById(bankAccountInsert.getClientId());

        bankAccount.setClient(client);

        return bankAccount;
    }

    public BankAccountList convertDbObjToBankAccountList(BankAccount bankAccount) {
        BankAccountList bankAccountList = new BankAccountList();

        bankAccountList.setId(bankAccount.getId());
        bankAccountList.setBankName(bankAccount.getBankName());
        bankAccountList.setCardNumber(bankAccount.getCardNumber());
        bankAccountList.setCVV(bankAccount.getCVV());
        bankAccountList.setExpiryDate(bankAccount.getExpiryDate());

        return bankAccountList;
    }
}

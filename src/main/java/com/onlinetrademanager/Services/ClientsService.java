package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.Clients.ClientEdit;
import com.onlinetrademanager.DataTransferObjects.Clients.ClientList;
import com.onlinetrademanager.Exceptions.NotFoundException;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Services are a layer in the MVC design architecture which is used for building this application.
 * They describe the behaviour of models.
 * The current service is used to give functionality to objects of type Client (Customer).
 * **/

@Service
@Transactional
public class ClientsService {
    private final ClientsRepository clientsRepository;
    private final BankAccountsService bankAccountsService;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository, BankAccountsService bankAccountsService) {
        this.clientsRepository = clientsRepository;
        this.bankAccountsService = bankAccountsService;
    }

    public UUID insertClient(Client client) {
        client.setPassword(BCrypt.hashpw(client.getPassword(), BCrypt.gensalt(12)));
        // Hashes the password, so we do not see it in the Database

        clientsRepository.save(client);

        return client.getId();
    }

    public ClientList findClientById(UUID id) throws NotFoundException {
        return clientsRepository.findClientById(id)
                .stream()
                .filter(Client::isActive)
                .map(this::convertDbObjToClientList)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User " + id + " not found."));
    }

    public List<ClientList> findAllClients() {
        return clientsRepository.findAll()
                .stream()
                .map(this::convertDbObjToClientList)
                .collect(Collectors.toList());
    }

    public boolean updateClient(ClientEdit toEdit) {
        boolean updated;

        if (toEdit.getPassword() != null) {
            toEdit.setPassword(BCrypt.hashpw(toEdit.getPassword(), BCrypt.gensalt(12)));
            // Hashes the password, so we do not see it in the Database
        } else {
            toEdit.setPassword(clientsRepository.findClientById(toEdit.getId())
                    .orElseThrow(() -> new NotFoundException("User " + toEdit.getId() + " not found."))
                    .getPassword());
        }
        Client client = convertClientEditToDbObj(toEdit);

        clientsRepository.save(client);

        updated = true;

        return updated;
    }

    /** region Converter methods **/

    private ClientList convertDbObjToClientList(Client client) {
        ClientList clientList = new ClientList();

        clientList.setId(client.getId());
        clientList.setDtype(client.getDtype());
        clientList.setEmail(client.getEmail());
        clientList.setUsername(client.getUsername());
        //clientList.getBankAccounts().addAll(client.getBankAccounts());

//        if (client.getBankAccounts() != null && !client.getBankAccounts().isEmpty()) {
//            for (BankAccount bankAccount : client.getBankAccounts()) {
//                BankAccountList bankAccountList = bankAccountsService.convertDbObjToBankAccountList(bankAccount);
//                clientList.addBankAccount(bankAccountList);
//            }
//        }

        return clientList;
    }

    private Client convertClientEditToDbObj(ClientEdit model) {
        Client client = new Client();

        client.setId(model.getId());
        client.setUsername(model.getUsername());
        client.setEmail(model.getEmail());
        client.setActive(model.isActive());
        client.setPassword(model.getPassword());

        client.getBankAccounts().addAll(clientsRepository.findClientById(model.getId())
                .orElseThrow(() -> new NotFoundException("User " + model.getId() + " not found."))
                .getBankAccounts());

        return client;
    }
}

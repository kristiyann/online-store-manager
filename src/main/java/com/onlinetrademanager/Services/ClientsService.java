package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.Clients.ClientEdit;
import com.onlinetrademanager.DataTransferObjects.Clients.ClientList;
import com.onlinetrademanager.DataTransferObjects.Relations.ItemsInClientCart;
import com.onlinetrademanager.DataTransferObjects.XRefs.XRefClientsItemsList;
import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Enums.Users.SiteTheme;
import com.onlinetrademanager.Enums.Users.UserRole;
import com.onlinetrademanager.Exceptions.NotFoundException;
import com.onlinetrademanager.Models.ClientBlockedCategoriesOnFeed;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Models.XRefClientsItems;
import com.onlinetrademanager.Repositories.ClientBlockedCategoriesOnFeedRepository;
import com.onlinetrademanager.Repositories.ClientsRepository;
import com.onlinetrademanager.Repositories.ItemsRepository;
import com.onlinetrademanager.Repositories.XRefClientsItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
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
    private final ItemsRepository itemsRepository;
    private final ItemsService itemsService;
    private final XRefClientsItemsRepository xRefClientsItemsRepository;
    private  final ClientBlockedCategoriesOnFeedRepository clientBlockedCategoriesOnFeedRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository,
                          BankAccountsService bankAccountsService,
                          ItemsRepository itemsRepository,
                          ItemsService itemsService,
                          XRefClientsItemsRepository xRefClientsItemsRepository,
                          ClientBlockedCategoriesOnFeedRepository clientBlockedCategoriesOnFeedRepository) {
        this.clientsRepository = clientsRepository;
        this.bankAccountsService = bankAccountsService;
        this.itemsRepository = itemsRepository;
        this.itemsService = itemsService;
        this.xRefClientsItemsRepository = xRefClientsItemsRepository;
        this.clientBlockedCategoriesOnFeedRepository = clientBlockedCategoriesOnFeedRepository;
    }

    public UUID insertClient(Client client) {
        client.setPreferredUserTheme(SiteTheme.DEFAULT);
        client.setPassword(BCrypt.hashpw(client.getPassword(), BCrypt.gensalt(12)));
        client.setRole(UserRole.CLIENT);
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

    public ClientList addRemoveItemToCart(ItemsInClientCart itemsInClientCart) {
        Client client = clientsRepository.findClientById(itemsInClientCart.getClientId())
                .orElseThrow(() -> new NotFoundException("Client " + itemsInClientCart.getClientId() + "not found!"));

        Item item = itemsRepository.findItemById(itemsInClientCart.getItemId())
                .orElseThrow(() -> new NotFoundException("Item " + itemsInClientCart.getItemId() + "not found!"));

        XRefClientsItems xRefClientsItems;
        if (itemsInClientCart.isInsert()) {
            xRefClientsItems = xRefClientsItemsRepository.getByClientAndItem(client, item)
                    .orElse(null);

            if (xRefClientsItems != null) {
                xRefClientsItems.setItemQuantity(xRefClientsItems.getItemQuantity() + 1);
            } else {
                xRefClientsItems = new XRefClientsItems();
                xRefClientsItems.setClient(client);
                xRefClientsItems.setItem(item);
                xRefClientsItems.setCreateDate(LocalDate.now());
                xRefClientsItems.setItemQuantity(1);
            }

            client.getCart().add(xRefClientsItems);
            item.getClientCarts().add(xRefClientsItems);
            xRefClientsItemsRepository.save(xRefClientsItems);
        } else {
            xRefClientsItems = xRefClientsItemsRepository.getByClientAndItem(client, item)
                    .orElseThrow(() -> new NotFoundException("Client: " + client.getId() + "'s card doesn't contain item with id: " + item.getId()));

            xRefClientsItems.setItemQuantity(xRefClientsItems.getItemQuantity() - 1);

            if (xRefClientsItems.getItemQuantity() <= 0) {
                client.getCart().remove(xRefClientsItems);
                item.getClientCarts().remove(xRefClientsItems);
                xRefClientsItemsRepository.delete(xRefClientsItems);
            }
        }

        clientsRepository.save(client);
        itemsRepository.save(item);

        return convertDbObjToClientList(client);
    }

    // TODO Test
    public boolean blockUnblockCategoryOnFeed(UUID clientId, ItemCategory category, boolean block) {
        Client client = clientsRepository.findClientById(clientId)
                .orElseThrow(() -> new NotFoundException("Client " + clientId + "not found!"));

        ClientBlockedCategoriesOnFeed clientBlock;

        if (block) {
            clientBlock = clientBlockedCategoriesOnFeedRepository.findByClientAndCategory(client, category)
                    .orElse(null);

            if (clientBlock == null) {
                clientBlock.setClient(client);
                clientBlock.setCategory(category);
            }

            clientBlockedCategoriesOnFeedRepository.save(clientBlock);
        } else {
            clientBlock = clientBlockedCategoriesOnFeedRepository.findByClientAndCategory(client, category)
                    .orElse(null);

            if (clientBlock != null) {
                clientBlockedCategoriesOnFeedRepository.delete(clientBlock);
            }
        }

        return true;
    }

    /** region Converter methods **/

    public ClientList convertDbObjToClientList(Client client) {
        ClientList clientList = new ClientList();

        clientList.setId(client.getId());
        clientList.setDtype(client.getDtype());
        clientList.setEmail(client.getEmail());
        clientList.setPreferredUserTheme(client.getPreferredUserTheme());
        clientList.setUsername(client.getUsername());

        clientList.setCart(new HashSet<>());
        for (XRefClientsItems xRefClientsItems : client.getCart()) {
            XRefClientsItemsList xRefClientsItemsList = new XRefClientsItemsList();

            xRefClientsItemsList.setxRefId(xRefClientsItems.getId());
            xRefClientsItemsList.setItem(itemsService.convertDbObjToList(xRefClientsItems.getItem()));
            xRefClientsItemsList.setCreateDate(xRefClientsItems.getCreateDate());
            xRefClientsItemsList.setItemQuantity(xRefClientsItems.getItemQuantity());

            clientList.getCart().add(xRefClientsItemsList);
        }

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

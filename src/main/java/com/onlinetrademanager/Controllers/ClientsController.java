package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.DataTransferObjects.Clients.ClientEdit;
import com.onlinetrademanager.DataTransferObjects.Clients.ClientList;
import com.onlinetrademanager.DataTransferObjects.Relations.ItemsInClientCart;
import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Services.ClientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controllers are the most exposed layer of the MVC architecture which is used for creating this application.
 * With them, we can access data in the database and fetch, add, modify, or delete it.
 * Controllers create addresses which are used by the front-end part of the application.
 * These addresses follow the format: http://localhost:8080/api/{table name}.
 * Spring applications run on the 8080 port by default.
 * Controllers' addresses redirect and trigger methods from the Service layer
 * We can pass parameters in a couple ways:
 * @PathVariable - requires the parameters to be set in the address string
 * @RequestBody - requires the parameters to be set in the JSON body
 * The current controller is used for operations on Client (Customer).
 * **/

@RestController
@RequestMapping("/api/Client")
public class ClientsController {
    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @PostMapping
    public ResponseEntity<UUID> insertClient(@RequestBody Client client) {
        UUID  clientId = clientsService.insertClient(client);
        return new ResponseEntity<>(clientId, HttpStatus.CREATED);
    }

    @GetMapping("/All")
    public ResponseEntity<List<ClientList>> getClientList() {
        List<ClientList> clients = clientsService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ClientList> getClient(@RequestParam UUID id) {
        ClientList client = clientsService.findClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateClient(@RequestBody ClientEdit client) {
        boolean updated = clientsService.updateClient(client);
        if (updated) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/AddRemoveFromCart")
    public ResponseEntity<ClientList> addRemoveFromCart(@RequestBody ItemsInClientCart itemsInClientCart) {
        ClientList clientList = clientsService.addRemoveItemToCart(itemsInClientCart);

        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @PutMapping("/BlockUnblockCategory")
    public ResponseEntity<Boolean> blockUnblockCategory(@RequestParam UUID clientId,@RequestParam ItemCategory category,@RequestParam boolean block) {
        clientsService.blockUnblockCategoryOnFeed(clientId, category, block);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

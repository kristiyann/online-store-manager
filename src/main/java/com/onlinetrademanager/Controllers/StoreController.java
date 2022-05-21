package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Services.OrderService;
import com.onlinetrademanager.Services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStore(UUID id) {
        Store store = storeService.findStoreById(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<Store>> getAllOrders() {
        List<Store> stores = storeService.findAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Store> addStore(@RequestBody Store store) {
        Store newStore = null;
        try {
            newStore = storeService.insertStore(store);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(newStore, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Store> updateOrder(@RequestBody Store store) {
        Store updateStore = null;
        try {
            updateStore = storeService.updateStore(store);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateStore, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteStore(@RequestParam UUID id) {
        storeService.deleteStoreById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

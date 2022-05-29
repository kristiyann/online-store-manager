package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.DataTransferObjects.Stores.StoreEdit;
import com.onlinetrademanager.DataTransferObjects.Stores.StoreList;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Services.StoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Store")
public class StoresController {

    private final StoresService storesService;

    public StoresController(StoresService storesService){
        this.storesService = storesService;
    }

    @GetMapping
    public ResponseEntity<StoreList> getStore(@RequestParam UUID id) {
        StoreList store = storesService.findStoreById(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @GetMapping("/User")
    public ResponseEntity<List<StoreList>> getAllStoresByUser(@RequestParam UUID userId) {
        List<StoreList> stores = storesService.findAllStoresByUser(userId);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<StoreList>> getAllStores() {
        List<StoreList> stores = storesService.findAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> addStore(@RequestBody StoreEdit store) {
        UUID storeId = null;
        try {
            storeId = storesService.insertStore(store);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(storeId, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Store> updateStore(@RequestBody StoreEdit store) {
        Store updateStore = null;
        try {
            updateStore = storesService.updateStore(store);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateStore, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteStore(@RequestParam UUID id) {
        storesService.deleteStoreById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

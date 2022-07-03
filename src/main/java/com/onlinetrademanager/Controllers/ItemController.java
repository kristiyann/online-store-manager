package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.Models.Image;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Services.ImageService;
import com.onlinetrademanager.Services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/Item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(UUID id) {
        Item item = itemService.findItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.findAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/All_By_Deleted")
    public ResponseEntity<List<Item>> getAllItemsByDeleted() {
        List<Item> items = itemService.findAllItemsByDeleted(false);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/All_By_Store")
    public ResponseEntity<List<Item>> getAllItemsByStore(UUID id) {
        List<Item> items = itemService.findAllItemsByStore(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item newItem = itemService.insertItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        Item updateItem = null;
        try {
            updateItem = itemService.updateItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }

    @PutMapping("/Update_Deleted")
    public ResponseEntity<Item> updateItemDeleted(@RequestBody Item item) {
        Item updateItem = null;
        try {
            item.setDeleted(true);
            updateItem = itemService.updateItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteItem(@RequestParam UUID id) {
        itemService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

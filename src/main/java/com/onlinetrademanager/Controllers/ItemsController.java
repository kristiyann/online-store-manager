package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.DataTransferObjects.Items.ItemEdit;
import com.onlinetrademanager.DataTransferObjects.Items.ItemList;
import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Enums.SortColumn;
import com.onlinetrademanager.Enums.SortOrder;
import com.onlinetrademanager.Services.ItemsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/Item")
public class ItemsController {

    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService){
        this.itemsService = itemsService;
    }

    @GetMapping
    public ResponseEntity<ItemList> getItem(@RequestParam UUID id) {
        ItemList item = itemsService.findItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<ItemList>> getAllItems(Integer skip,
                                                      Integer top,
                                                      String searchKeyword,
                                                      BigDecimal priceFrom,
                                                      BigDecimal priceTo,
                                                      ItemCategory category,
                                                      SortOrder sortOrder,
                                                      SortColumn sortColumn) {
        List<ItemList> items = itemsService.findAllItems(skip, top, searchKeyword, priceFrom, priceTo, category, sortOrder, sortColumn);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/Store")
    public ResponseEntity<List<ItemList>> getAllItemsByStore(@RequestParam UUID storeId,
                                                             Integer skip,
                                                             Integer top,
                                                             String searchKeyword,
                                                             BigDecimal priceFrom,
                                                             BigDecimal priceTo,
                                                             ItemCategory category,
                                                             SortOrder sortOrder,
                                                             SortColumn sortColumn) {
        List<ItemList> items = itemsService.findAllItemsByStore(storeId, skip, top, searchKeyword, priceFrom, priceTo, category, sortOrder, sortColumn);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insert(@RequestBody ItemEdit item) {
        UUID itemId = itemsService.insertItem(item);
        return new ResponseEntity<>(itemId, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ItemList> updateItem(@RequestBody ItemEdit item) {
        ItemList updateItem = null;
        try {
            updateItem = itemsService.updateItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteItem(@RequestParam UUID id) {
        itemsService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

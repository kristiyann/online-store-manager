package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.DataTransferObjects.Items.FrontPageFeed;
import com.onlinetrademanager.DataTransferObjects.Items.ItemEdit;
import com.onlinetrademanager.DataTransferObjects.Items.ItemList;
import com.onlinetrademanager.DataTransferObjects.ResponseViewmodel;
import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Enums.SortColumn;
import com.onlinetrademanager.Enums.SortOrder;
import com.onlinetrademanager.Services.ItemsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/Feed")
    public ResponseEntity<FrontPageFeed> getItem(@RequestParam Optional<UUID> clientId, Integer top) {
        FrontPageFeed feed = itemsService.getFrontPageItemFeed(top, clientId.orElse(null));
        return new ResponseEntity<>(feed, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<ResponseViewmodel> getAllItems(Integer skip,
                                                      Integer top,
                                                      String searchKeyword,
                                                      BigDecimal priceFrom,
                                                      BigDecimal priceTo,
                                                      ItemCategory category,
                                                      SortOrder sortOrder,
                                                      SortColumn sortColumn) {
        ResponseViewmodel items = itemsService.findAllItems(skip, top, searchKeyword, priceFrom, priceTo, category, sortOrder, sortColumn);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/Store")
    public ResponseEntity<ResponseViewmodel> getAllItemsByStore(@RequestParam UUID storeId,
                                                                Integer skip,
                                                                Integer top,
                                                                String searchKeyword,
                                                                BigDecimal priceFrom,
                                                                BigDecimal priceTo,
                                                                ItemCategory category,
                                                                SortOrder sortOrder,
                                                                SortColumn sortColumn) {
        ResponseViewmodel items = itemsService.findAllItemsByStore(storeId, skip, top, searchKeyword, priceFrom, priceTo, category, sortOrder, sortColumn);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/Ids")
    public ResponseEntity<List<ItemList>> getAllItemsByIds(@RequestParam List<UUID> ids) {
        List<ItemList> items = itemsService.findItemsByIds(ids);
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

    @PutMapping("/Delete")
    public ResponseEntity<Boolean> updateSaleDeleted(@RequestParam UUID id) {
        itemsService.updateItemDeleted(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

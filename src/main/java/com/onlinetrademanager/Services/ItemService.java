package com.onlinetrademanager.Services;

import com.onlinetrademanager.Exceptions.ItemNotFoundException;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Repositories.ItemRepository;
import com.onlinetrademanager.Repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {

    public final ItemRepository itemRepository;
    public final StoreRepository storeRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, StoreRepository storeRepository){
        this.itemRepository = itemRepository;
        this.storeRepository = storeRepository;
    }

    public Item insertItem(Item item){
        item.setDeleted(false);
        itemRepository.save(item);
        return item;
    }

    public Item updateItem(Item item){
        Item itemUpd = new Item(item);
        itemRepository.save(itemUpd);
        return itemUpd;
    }

    public Item updateItemDeleted(Item item){
        Item itemUpd = new Item(item);
        item.setDeleted(true);
        itemRepository.save(itemUpd);
        return itemUpd;
    }

    public void deleteItem(Item item){
        itemRepository.delete(item);
    }

    public void deleteItemById(UUID id){
        itemRepository.deleteItemById(id);
    }

    public Item findItemById(UUID id){
        return itemRepository.findItemById(id).orElseThrow(()
                -> new ItemNotFoundException("Item " + id + "not found!"));
    }

    public List<Item> findAllItems(){
        return itemRepository.findAll().stream().filter(a -> a.isDeleted()).collect(Collectors.toList());
    }

    public List<Item> findAllItemsByStore(UUID id){

        Store store = storeRepository.findStoreById(id).orElseThrow(()
                -> new ItemNotFoundException("Store " + id + "not found!"));
        return itemRepository.findAllItemsByStore(store);
    }

    /*
    public List<Item> findAllItemsByDeleted(boolean deleted){
        return itemRepository.findAllItemsByDeleted(deleted);
    }
    */

}

package com.onlinetrademanager.Services;

import com.onlinetrademanager.Exceptions.ItemNotFoundException;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Repositories.ItemRepository;
import com.onlinetrademanager.Repositories.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ItemService {

    public final ItemRepository itemRepository;
    public final StoresRepository storesRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, StoresRepository storesRepository){
        this.itemRepository = itemRepository;
        this.storesRepository = storesRepository;
    }

    public Item insertItem(Item item){
        itemRepository.save(item);
        return item;
    }

    public Item updateItem(Item item){
        Item itemUpd = new Item(item);
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
        return itemRepository.findAll();
    }

    public List<Item> findAllItemsByStore(UUID storeId){

        Store store = storesRepository.findStoreById(storeId).orElseThrow(()
                -> new ItemNotFoundException("Store " + storeId + "not found!"));
        return itemRepository.findAllImagesByStore(store);
    }

}

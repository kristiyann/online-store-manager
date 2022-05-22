package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.Items.ItemEdit;
import com.onlinetrademanager.DataTransferObjects.Items.ItemList;
import com.onlinetrademanager.Exceptions.ItemNotFoundException;
import com.onlinetrademanager.Exceptions.SaleNotFoundException;
import com.onlinetrademanager.Exceptions.StoreNotFoundException;
import com.onlinetrademanager.Models.Image;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Sale;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Repositories.ImageRepository;
import com.onlinetrademanager.Repositories.ItemsRepository;
import com.onlinetrademanager.Repositories.SaleRepository;
import com.onlinetrademanager.Repositories.StoresRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemsService {
    public final ItemsRepository itemsRepository;
    public final StoresRepository storesRepository;
    public final SaleRepository saleRepository;
    public final ImageRepository imageRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository, StoresRepository storesRepository, SaleRepository saleRepository, ImageRepository imageRepository){
        this.itemsRepository = itemsRepository;
        this.storesRepository = storesRepository;
        this.saleRepository = saleRepository;
        this.imageRepository = imageRepository;
    }

    public UUID insertItem(ItemEdit item){
        Item dbObj = convertEditToDbObj(item);

        dbObj.setCreateDate(LocalDate.now());

        Set<Image> images = new HashSet<>();
        for (String url : item.getImageUrls()) {
            Image image = new Image();
            image.setItem(dbObj);
            image.setUrl(url);

            this.imageRepository.save(image);
        }

        dbObj.setImages(images);

        itemsRepository.save(dbObj);
        return dbObj.getId();
    }

    public Item updateItem(ItemEdit item){
        Item itemUpd = convertEditToDbObj(item);
        itemsRepository.save(itemUpd);
        return itemUpd;
    }

    public void deleteItem(Item item){
        itemsRepository.delete(item);
    }

    public void deleteItemById(UUID id){
        itemsRepository.deleteItemById(id);
    }

    public ItemList findItemById(UUID id){
        return itemsRepository.findItemById(id)
                .stream()
                .map(this::convertDbObjToList)
                .findFirst()
                .orElseThrow(()
                -> new ItemNotFoundException("Item " + id + "not found!"));
    }

    public List<ItemList> findAllItems(){
        return itemsRepository.findAll()
                .stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    public List<ItemList> findAllItemsByStore(UUID storeId){

        Store store = storesRepository.findStoreById(storeId).orElseThrow(()
                -> new ItemNotFoundException("Store " + storeId + "not found!"));
        return itemsRepository.findAllImagesByStore(store)
                .stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    //#region Helper methods

    private Item convertEditToDbObj(ItemEdit itemEdit) {
        Item item = new Item();

        Store store = storesRepository.findStoreById(itemEdit.getStoreId())
                .orElseThrow(() -> new StoreNotFoundException("Store with id" + itemEdit.getStoreId() + "not found."));

        Sale sale = saleRepository.findSaleById(itemEdit.getSaleId())
                .orElse(null);

        item.setId(itemEdit.getId());
        item.setCategory(itemEdit.getCategory());
        item.setChangeDate(LocalDate.now());
        item.setDescription(itemEdit.getDescription());
        item.setPrice(itemEdit.getPrice());
        item.setTitle(itemEdit.getTitle());
        item.setSale(sale);
        item.setStore(store);

        return item;
    }

    private ItemList convertDbObjToList(Item item) {
        ItemList itemList = new ItemList();

        itemList.setId(item.getId());
        itemList.setCategory(item.getCategory());
        itemList.setChangeDate(item.getChangeDate());
        itemList.setCreateDate(item.getCreateDate());
        itemList.setDescription(item.getDescription());
        itemList.setTitle(item.getTitle());
        itemList.setStoreId(item.getStore().getId());
        itemList.setOriginalPrice(item.getPrice());

        List<String> imageUrls = new ArrayList<>();
        // TODO
        for (Image image : item.getImages()) {
            imageUrls.add(image.getUrl());
        }

        itemList.setImages(imageUrls);

        if (item.getSale() != null) {
            if (LocalDate.now().isAfter(item.getSale().getStartDate()) && LocalDate.now().isBefore(item.getSale().getEndDate())) {
                double percentage = item.getPrice().intValue() * item.getSale().getSalePercentage() * 0.01;

                BigDecimal newPrice = item.getPrice().subtract(BigDecimal.valueOf(percentage));

                itemList.setPrice(newPrice);
            }
        } else {
            itemList.setPrice(item.getPrice());
        }

        return itemList;
    }
}

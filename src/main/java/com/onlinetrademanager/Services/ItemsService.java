package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.GenericComboBox;
import com.onlinetrademanager.DataTransferObjects.Items.ItemEdit;
import com.onlinetrademanager.DataTransferObjects.Items.ItemList;
import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Enums.SortColumn;
import com.onlinetrademanager.Enums.SortOrder;
import com.onlinetrademanager.Exceptions.ItemNotFoundException;
import com.onlinetrademanager.Exceptions.StoreNotFoundException;
import com.onlinetrademanager.Models.*;
import com.onlinetrademanager.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ItemsService {
    public final ItemsRepository itemsRepository;
    public final StoresRepository storesRepository;
    public final SaleRepository saleRepository;
    public final ImageRepository imageRepository;
    public final ClientsRepository clientsRepository;
    public final XRefOrdersItemsRepository xRefOrdersItemsRepository;
    public final XRefClientsItemsRepository xRefClientsItemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository,
                        StoresRepository storesRepository,
                        SaleRepository saleRepository,
                        ImageRepository imageRepository,
                        ClientsRepository clientsRepository,
                        XRefOrdersItemsRepository xRefOrdersItemsRepository,
                        XRefClientsItemsRepository xRefClientsItemsRepository){
        this.itemsRepository = itemsRepository;
        this.storesRepository = storesRepository;
        this.saleRepository = saleRepository;
        this.imageRepository = imageRepository;
        this.clientsRepository = clientsRepository;
        this.xRefOrdersItemsRepository = xRefOrdersItemsRepository;
        this.xRefClientsItemsRepository = xRefClientsItemsRepository;
    }

    public UUID insertItem(ItemEdit item){
        Item dbObj = convertEditToDbObj(item);

        // dbObj.setCreateDate(LocalDate.now());

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

    public ItemList updateItem(ItemEdit item){
        Item itemUpd = convertEditToDbObj(item);

        itemsRepository.save(itemUpd);
        return convertDbObjToList(itemUpd);
    }

    public void deleteItem(Item item){
        itemsRepository.delete(item);
    }

    public void deleteItemById(UUID id){
        Item toDelete = itemsRepository.findItemById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item " + id + "not found!"));

        if (toDelete.getImages() != null  && !toDelete.getImages().isEmpty()) {
            List<Image> images = imageRepository.findAllImagesByItem(toDelete).stream().toList();
            imageRepository.deleteAll(images);
        }
        if (toDelete.getOrders() != null  && !toDelete.getOrders().isEmpty()) {
            xRefOrdersItemsRepository.deleteAll(toDelete.getOrders());
        }
        if (toDelete.getClientCarts() != null  && !toDelete.getClientCarts().isEmpty()) {
            xRefClientsItemsRepository.deleteAll(toDelete.getClientCarts());
        }
        // itemsRepository.deleteItemById(id);

        itemsRepository.delete(toDelete);
    }

    public ItemList findItemById(UUID id){
        return itemsRepository.findItemById(id)
                .stream()
                .map(this::convertDbObjToList)
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Item " + id + "not found!"));
    }

    public List<ItemList> findAllItems(Integer skip,
                                       Integer top,
                                       String searchKeyword,
                                       BigDecimal priceFrom,
                                       BigDecimal priceTo,
                                       ItemCategory category,
                                       SortOrder sortOrder,
                                       SortColumn sortColumn){
        List<Item> query = itemsRepository.findAll();

        query = applyFiltering(query, searchKeyword, priceFrom, priceTo, category);

        query = applySorting(query, sortOrder, sortColumn);

        query = applyPagination(query, skip, top);

        return query.stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    public List<ItemList> findAllItemsByStore(UUID storeId,
                                              Integer skip,
                                              Integer top,
                                              String searchKeyword,
                                              BigDecimal priceFrom,
                                              BigDecimal priceTo,
                                              ItemCategory category,
                                              SortOrder sortOrder,
                                              SortColumn sortColumn) {
        Store store = storesRepository.findStoreById(storeId).orElseThrow(()
                -> new ItemNotFoundException("Store " + storeId + "not found!"));

        List<Item> query =  itemsRepository.findAllImagesByStore(store);

        query = applyFiltering(query, searchKeyword, priceFrom, priceTo, category);

        query = applySorting(query, sortOrder, sortColumn);

        query = applyPagination(query, skip, top);

        return query.stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    //#region Helper methods

    private Item convertEditToDbObj(ItemEdit itemEdit) {
        Item item = itemsRepository.findItemById(itemEdit.getId())
                .orElse(null);

        if (item == null) {
            item = new Item();
        }

        Store store = storesRepository.findStoreById(itemEdit.getStoreId())
                .orElseThrow(() -> new StoreNotFoundException("Store with id" + itemEdit.getStoreId() + "not found."));

        Sale sale = saleRepository.findSaleById(itemEdit.getSaleId())
                .orElse(null);

        item.setId(itemEdit.getId());
        item.setCategory(itemEdit.getCategory());
        item.setChangeDate(LocalDateTime.now());
        item.setDescription(itemEdit.getDescription());
        item.setPrice(itemEdit.getPrice());
        item.setTitle(itemEdit.getTitle());
        item.setSale(sale);
        item.setStore(store);

        return item;
    }

    public ItemList convertDbObjToList(Item item) {
        ItemList itemList = new ItemList();

        itemList.setId(item.getId());
        itemList.setCategory(item.getCategory());
        itemList.setChangeDate(item.getChangeDate());
        itemList.setCreateDate(item.getCreateDate());
        itemList.setDescription(item.getDescription());
        itemList.setTitle(item.getTitle());
        itemList.setOriginalPrice(item.getPrice());

        GenericComboBox store = new GenericComboBox();
        store.setValue(item.getStore().getId());
        store.setText(item.getStore().getName());
        itemList.setStore(store);

        List<String> imageUrls = new ArrayList<>();
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

    public static List<Item> applyPagination(List<Item> query, Integer skip, Integer top) {
        if (skip == null) {
            skip = 0;
        }

        query = query.stream().skip(skip).collect(Collectors.toList());

        if (top != null) {
            query = query.stream().limit(top).collect(Collectors.toList());
        }

        return query;
    }

    public static List<Item> applyFiltering(List<Item> query, String searchKeyword, BigDecimal priceFrom, BigDecimal priceTo, ItemCategory category) {
        Stream<Item> stream = query.stream();

        if (searchKeyword != null) {
            stream = stream.filter(item -> item.getTitle().contains(searchKeyword));
        }

        if (priceFrom != null) {
            stream = stream.filter(item -> item.getPrice().compareTo(priceFrom) >= 0);
        }

        if (priceTo != null) {
            stream = stream.filter(item -> item.getPrice().compareTo(priceTo) <= 0);
        }

        if (category != null) {
            stream = stream.filter(item -> item.getCategory().equals(category));
        }

        return stream.collect(Collectors.toList());
    }

    public static List<Item> applySorting(List<Item> query, SortOrder sortOrder, SortColumn sortColumn) {
        Stream<Item> stream = query.stream();

        if (sortColumn != null) {
            switch (sortColumn) {
                case PRICE -> stream = stream.sorted((o1, o2) -> Integer.compare(o1.getPrice().compareTo(o2.getPrice()), 0));
            }

            if (sortOrder == SortOrder.DESC) {
                List<Item> list = stream.collect(Collectors.toList());

                Collections.reverse(list);

                stream = list.stream();
            }
        }

        return stream.collect(Collectors.toList());
    }
}

package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.GenericComboBox;
import com.onlinetrademanager.DataTransferObjects.Items.FrontPageFeed;
import com.onlinetrademanager.DataTransferObjects.Items.ItemEdit;
import com.onlinetrademanager.DataTransferObjects.Items.ItemList;
import com.onlinetrademanager.DataTransferObjects.ResponseViewmodel;
import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Enums.SortColumn;
import com.onlinetrademanager.Enums.SortOrder;
import com.onlinetrademanager.Exceptions.ItemNotFoundException;
import com.onlinetrademanager.Exceptions.StoreNotFoundException;
import com.onlinetrademanager.Models.*;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    public final ClientBlockedCategoriesOnFeedRepository clientBlockedCategoriesOnFeedRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository,
                        StoresRepository storesRepository,
                        SaleRepository saleRepository,
                        ImageRepository imageRepository,
                        ClientsRepository clientsRepository,
                        XRefOrdersItemsRepository xRefOrdersItemsRepository,
                        XRefClientsItemsRepository xRefClientsItemsRepository,
    ClientBlockedCategoriesOnFeedRepository clientBlockedCategoriesOnFeedRepository){
        this.itemsRepository = itemsRepository;
        this.storesRepository = storesRepository;
        this.saleRepository = saleRepository;
        this.imageRepository = imageRepository;
        this.clientsRepository = clientsRepository;
        this.xRefOrdersItemsRepository = xRefOrdersItemsRepository;
        this.xRefClientsItemsRepository = xRefClientsItemsRepository;
        this.clientBlockedCategoriesOnFeedRepository = clientBlockedCategoriesOnFeedRepository;
    }

    public UUID insertItem(ItemEdit item) {
        Item dbObj = convertEditToDbObj(item);
        dbObj.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        dbObj.setChangeDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        dbObj.setDeleted(false);

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

    public ItemList updateItem(ItemEdit item) {
        Item dbObj =  itemsRepository.findItemById(item.getId())
                .filter(a -> !a.isDeleted())
                .orElseThrow(() -> new ItemNotFoundException("Item " + item.getId() + "not found!"));

        convertEditToDbObj(dbObj, item);
        dbObj.setChangeDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        itemsRepository.save(dbObj);
        return convertDbObjToList(dbObj);
    }

    public void updateItemDeleted(UUID id){
        Item dbObj =  itemsRepository.findItemById(id)
                .filter(a -> !a.isDeleted())
                .orElseThrow(() -> new ItemNotFoundException("Item " + id + "not found!"));

        dbObj.setDeleted(true);
        itemsRepository.save(dbObj);
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
                .filter(a -> !a.isDeleted())
                .map(this::convertDbObjToList)
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Item " + id + "not found!"));
    }

    public ResponseViewmodel findAllItems(Integer skip,
                                       Integer top,
                                       String searchKeyword,
                                       BigDecimal priceFrom,
                                       BigDecimal priceTo,
                                       ItemCategory category,
                                       SortOrder sortOrder,
                                       SortColumn sortColumn){
        List<Item> query = itemsRepository.findAll().stream().filter(a -> !a.isDeleted()).collect(Collectors.toList());

        query = applyFiltering(query, searchKeyword, priceFrom, priceTo, category);

        query = applySorting(query, sortOrder, sortColumn);

        query = applyPagination(query, skip, top);

        List<ItemList> list = query.stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());

        return new ResponseViewmodel(list, list.stream().count());
    }

    public ResponseViewmodel findAllItemsByStore(UUID storeId,
                                                 Integer skip,
                                                 Integer top,
                                                 String searchKeyword,
                                                 BigDecimal priceFrom,
                                                 BigDecimal priceTo,
                                                 ItemCategory category,
                                                 SortOrder sortOrder,
                                                 SortColumn sortColumn) {
        Store store = storesRepository.findStoreById(storeId)
                .orElseThrow(()
                -> new ItemNotFoundException("Store " + storeId + "not found!"));

        List<Item> query =  itemsRepository.findAllItemsByStore(store).stream().filter(a -> !a.isDeleted()).collect(Collectors.toList());

        query = applyFiltering(query, searchKeyword, priceFrom, priceTo, category);

        query = applySorting(query, sortOrder, sortColumn);

        query = applyPagination(query, skip, top);

        List<ItemList> list = query.stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());

        return new ResponseViewmodel(list, list.stream().count());
    }

    public List<ItemList> findItemsByIds(List<UUID> ids) {
        List<Item> query = itemsRepository.findItemsByIds(ids);

        return query.stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    public FrontPageFeed getFrontPageItemFeed(Integer top, UUID clientId) {
        FrontPageFeed feed = new FrontPageFeed();

        Client client;
        if (clientId != null) {
            client = clientsRepository.findClientById(clientId).orElse(null);
        } else {
            client = null;
        }

        if (top == null || top == 0 || top > 50) {
            top = 5;
        }

        List<Item> query = itemsRepository.findAll().stream().filter(a -> !a.isDeleted()).collect(Collectors.toList());
        query = applySorting(query, SortOrder.DESC, SortColumn.CREATEDATE);

        feed.setItemsVehicle(this.generateCategorisedList(query, top, ItemCategory.VEHICLES, client));
        feed.setItemsAntiques(this.generateCategorisedList(query, top, ItemCategory.ANTIQUES, client));
        feed.setItemsClothes(this.generateCategorisedList(query, top, ItemCategory.CLOTHES, client));
        feed.setItemsGames(this.generateCategorisedList(query, top, ItemCategory.GAMES, client));
        feed.setItemsOther(this.generateCategorisedList(query, top, ItemCategory.OTHER, client));
        feed.setItemsElectronics(this.generateCategorisedList(query, top, ItemCategory.ELECTRONICS, client));
        feed.setItemsSportHobby(this.generateCategorisedList(query, top, ItemCategory.SPORTANDHOBBY, client));
        feed.setItemsPets(this.generateCategorisedList(query, top, ItemCategory.PETS, client));

        return feed;
    }

    //#region Helper methods

    private Item convertEditToDbObj(ItemEdit itemEdit) {
        Item item = itemsRepository.findItemById(itemEdit.getId())
                .filter(a -> !a.isDeleted())
                .orElse(null);

        if (item == null) {
            item = new Item();
        }

        Store store = storesRepository.findStoreById(itemEdit.getStoreId())
                .filter(a -> a.getActive())
                .orElseThrow(() -> new StoreNotFoundException("Store with id" + itemEdit.getStoreId() + "not found."));

        Sale sale = saleRepository.findSaleById(itemEdit.getSaleId())
                .filter(a -> !a.isDeleted())
                .orElse(null);

        item.setId(itemEdit.getId());
        item.setCategory(itemEdit.getCategory());
        item.setDescription(itemEdit.getDescription());
        item.setPrice(itemEdit.getPrice());
        item.setTitle(itemEdit.getTitle());
        item.setSale(sale);
        item.setStore(store);

        return item;
    }

    private void convertEditToDbObj(Item dbObj, ItemEdit itemEdit) {
        Sale sale = saleRepository.findSaleById(itemEdit.getSaleId())
                .filter(a -> !a.isDeleted())
                .orElse(null);

        dbObj.setCategory(itemEdit.getCategory());
        dbObj.setDescription(itemEdit.getDescription());
        dbObj.setPrice(itemEdit.getPrice());
        dbObj.setTitle(itemEdit.getTitle());
        dbObj.setSale(sale);
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
                itemList.setSalePercentage(item.getSale().getSalePercentage());
                itemList.setSaleId(item.getSale().getId());
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
            stream = stream.filter(item -> item.getTitle().toLowerCase().contains(searchKeyword.toLowerCase()));
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
                case CREATEDATE -> stream = stream.sorted(Comparator.comparing(Item::getCreateDate));
            }

            if (sortOrder == SortOrder.DESC) {
                List<Item> list = stream.collect(Collectors.toList());

                Collections.reverse(list);

                stream = list.stream();
            }
        }

        return stream.collect(Collectors.toList());
    }

    private List<ItemList> generateCategorisedList(List<Item> query, Integer top, ItemCategory category, Client client) {
        if (client != null) {
            ClientBlockedCategoriesOnFeed clientBlockedCategoriesOnFeed = clientBlockedCategoriesOnFeedRepository.findByClientAndCategory(client, category)
                    .orElse(null);

            if (clientBlockedCategoriesOnFeed != null) {
                    List<ItemList> emptyList = new ArrayList<>();
                    return emptyList;
            }

        }

        List<ItemList> categorisedList = query.stream()
                .filter(item -> item.getCategory().equals(category))
                .filter(a -> !a.isDeleted())
                .limit(top)
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());

        return categorisedList;
    }
}

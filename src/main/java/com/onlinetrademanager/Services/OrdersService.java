package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.BatchChangeModel;
import com.onlinetrademanager.DataTransferObjects.Orders.OrderCustomFilter;
import com.onlinetrademanager.DataTransferObjects.Orders.OrderEdit;
import com.onlinetrademanager.DataTransferObjects.Orders.OrderInsert;
import com.onlinetrademanager.DataTransferObjects.Orders.OrderList;
import com.onlinetrademanager.DataTransferObjects.XRefs.XRefOrdersItemsList;
import com.onlinetrademanager.Enums.Order.OrderStatus;
import com.onlinetrademanager.Exceptions.NotFoundException;
import com.onlinetrademanager.Exceptions.OrderNotFoundException;
import com.onlinetrademanager.Models.*;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdersService {
    public final OrdersRepository ordersRepository;
    public final DeliveryCompaniesRepository deliveryCompaniesRepository;
    public final StoresRepository storesRepository;
    public final UsersRepository usersRepository;
    public final ClientsRepository clientsRepository;
    public final ItemsRepository itemsRepository;
    public final XRefOrdersItemsRepository xRefOrdersItemsRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository,
                         DeliveryCompaniesRepository deliveryCompaniesRepository,
                         StoresRepository storesRepository,
                         UsersRepository usersRepository,
                         ClientsRepository clientsRepository,
                         ItemsRepository itemsRepository,
                         XRefOrdersItemsRepository xRefOrdersItemsRepository){
        this.ordersRepository = ordersRepository;
        this.deliveryCompaniesRepository = deliveryCompaniesRepository;
        this.storesRepository = storesRepository;
        this.usersRepository = usersRepository;
        this.clientsRepository = clientsRepository;
        this.itemsRepository = itemsRepository;
        this.xRefOrdersItemsRepository = xRefOrdersItemsRepository;
    }

    public UUID insertOrder(OrderInsert orderInsert){
        Order order = convertInsertToDbObj(orderInsert);

        ordersRepository.save(order);

        this.addItemsToOrder(order.getId(), orderInsert.getItemIds());

        return order.getId();
    }

    public OrderList updateOrder(OrderEdit orderEdit){
        Order orderUpd = convertEditToDbObj(orderEdit);

        ordersRepository.save(orderUpd);

        // this.addItemsToOrder(orderUpd.getId(), orderEdit.getItemIds());

        return convertDbObjToList(orderUpd);
    }

    public OrderList batchChangeItems(BatchChangeModel batchChangeModel) {
        Order order = ordersRepository.findOrderById(batchChangeModel.getObjectId())
                .orElseThrow(() -> new NotFoundException("Order " + batchChangeModel.getObjectId() + "not found!"));

        if (batchChangeModel.isInsert()) {
            this.addItemsToOrder(order.getId(), batchChangeModel.getItemIds());
        } else {
//            for (XRefOrdersItems xRefOrdersItems : order.getItems()) {
//                Item item = itemsRepository.findItemById(xRefOrdersItems.getItem().getId())
//                        .orElseThrow(() -> new NotFoundException("Order " + xRefOrdersItems.getItem().getId() + "not found!"));
//
//                order.getItems().remove(xRefOrdersItems);
//                item.getOrders().remove(xRefOrdersItems);
//            }

//            ordersRepository.save(order);

            for (UUID itemId : batchChangeModel.getItemIds()) {
                Item item = itemsRepository.findItemById(itemId)
                        .orElseThrow(() -> new NotFoundException("Order " + itemId + "not found!"));

                XRefOrdersItems xRefOrdersItems = xRefOrdersItemsRepository.getByOrderAndItem(order, item)
                        .orElse(null);

                item.getOrders().remove(xRefOrdersItems);
                order.getItems().remove(xRefOrdersItems);

                if (xRefOrdersItems != null) {
                    this.xRefOrdersItemsRepository.delete(xRefOrdersItems);
                }
            }
        }

        return convertDbObjToList(order);
    }

    public void deleteOrderById(UUID id){
        ordersRepository.deleteOrderById(id);
    }

    public void deleteOrder(Order order){
        ordersRepository.delete(order);
    }

    public OrderList findOrderById(UUID id){
        return ordersRepository.findOrderById(id)
                .stream()
                .map(this::convertDbObjToList)
                .findFirst()
                .orElseThrow(()
                -> new OrderNotFoundException("Order " + id + "not found!"));
    }

    public List<OrderList> findAllOrdersByDeliveryCompanyId(UUID delCompId){
        DeliveryCompany deliveryCompany = deliveryCompaniesRepository.getById(delCompId);
        return ordersRepository.findAllOrdersByDeliveryCompany(deliveryCompany)
                .stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    public List<OrderList> findAllOrdersByClient(UUID clientId, OrderStatus status){
        Client client = clientsRepository.getById(clientId);
        List<Order> query = ordersRepository.findAllOrdersByClient(client);

        query = this.applyCustomFilter(query, status);

        return query.stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    public List<OrderList> findAllOrders(OrderStatus status){
        List<Order> query = ordersRepository.findAll();

        query = this.applyCustomFilter(query, status);

        return query.stream()
            .map(this::convertDbObjToList)
            .collect(Collectors.toList());
    }

//    public List<Order> findAllOrdersByStoreId(UUID storeId){
//        Store store = storesRepository.findStoreById(storeId).orElseThrow(()
//                -> new OrderNotFoundException("Store " + storeId + "not found!"));
//        return ordersRepository.findAllOrdersByStore(store);
//    }

    public void addItemsToOrder(UUID orderId, List<UUID> itemIds) {
        Order order = ordersRepository.findOrderById(orderId).orElseThrow(()
                -> new OrderNotFoundException("Order " + orderId + "not found!"));

        // List<Item> items = itemsRepository.findAllById(itemIds);
        XRefOrdersItems xRefOrdersItems;
        for (UUID itemId : itemIds) {
            Item item = itemsRepository.findItemById(itemId)
                    .orElseThrow(() -> new NotFoundException("Item " + itemId + "not found!"));

            order.setTotalPrice(order.getTotalPrice().add(item.getPrice()));

            xRefOrdersItems = xRefOrdersItemsRepository.getByOrderAndItem(order, item)
                    .orElse(null);

            if (xRefOrdersItems == null) {
                xRefOrdersItems = new XRefOrdersItems();
                xRefOrdersItems.setItem(item);
                xRefOrdersItems.setOrder(order);
                xRefOrdersItems.setItemQuantity(1);
            } else {
                xRefOrdersItems.setItemQuantity(xRefOrdersItems.getItemQuantity() + 1);
            }

            order.getItems().add(xRefOrdersItems);
            item.getOrders().add(xRefOrdersItems);
            this.xRefOrdersItemsRepository.save(xRefOrdersItems);
            this.itemsRepository.save(item);
        }

        this.ordersRepository.save(order);
    }

    //#region Helper methods

    private Order convertInsertToDbObj(OrderInsert orderInsert) {
        Order order = new Order();

        Client client = clientsRepository.findClientById(orderInsert.getUserId())
                .orElseThrow(() -> new NotFoundException("Client " + orderInsert.getUserId() + "not found!"));

        DeliveryCompany deliveryCompany = deliveryCompaniesRepository.findDeliveryCompanyById(orderInsert.getDeliveryCompanyId())
                .orElseThrow(() -> new NotFoundException("Delivery Company " + orderInsert.getDeliveryCompanyId() + "not found!"));

        order.setId(orderInsert.getId());
        order.setChangeDate(LocalDate.now());
        order.setDeliveryCompany(deliveryCompany);
        order.setStatus(OrderStatus.CREATED);
        order.setItems(new HashSet<>());
        order.setClient(client);
        order.setTotalPrice(deliveryCompany.getDeliveryFee());
        // order.setStore(items.get(1).getStore());

        return order;
    }

    private OrderList convertDbObjToList(Order order) {
        OrderList orderList = new OrderList();

        orderList.setId(order.getId());
        orderList.setCreateDate(order.getCreateDate());
        orderList.setStatus(order.getStatus());
        orderList.setChangeDate(order.getChangeDate());
        orderList.setTotalPrice(order.getTotalPrice());
        orderList.setUserId(order.getClient().getId());
        orderList.setDeliveryCompanyId(order.getDeliveryCompany().getId());

        Set<XRefOrdersItemsList> xRefCollection = new HashSet<>();
        for (XRefOrdersItems xRefOrdersItems : order.getItems()) {
            XRefOrdersItemsList xRefOrdersItemsList = new XRefOrdersItemsList();

            xRefOrdersItemsList.setItemId(xRefOrdersItems.getItem().getId());
            xRefOrdersItemsList.setxRefId(xRefOrdersItems.getId());
            xRefOrdersItemsList.setItemQuantity(xRefOrdersItems.getItemQuantity());

            xRefCollection.add(xRefOrdersItemsList);
        }

        orderList.setItems(xRefCollection);

        return orderList;
    }

    private Order convertEditToDbObj(OrderEdit orderEdit) {
        Order order = ordersRepository.findOrderById(orderEdit.getId())
                .orElseThrow(() -> new NotFoundException("Order " + orderEdit.getId() + "not found!"));

        DeliveryCompany deliveryCompany = deliveryCompaniesRepository.findDeliveryCompanyById(orderEdit.getDeliveryCompanyId())
                .orElseThrow(() -> new NotFoundException("Delivery Company " + orderEdit.getDeliveryCompanyId() + "not found!"));

        order.setStatus(orderEdit.getStatus());
        order.setChangeDate(LocalDate.now());
        order.setDeliveryCompany(deliveryCompany);
        order.setChangeDate(LocalDate.now());

        // order.setItems(new HashSet<>());

        return order;
    }

    private List<Order> applyCustomFilter(List<Order> query, OrderStatus customFilter) {
//        if (customFilter != null) {
//            if (customFilter.getStatus() != null) {
//                query = query.stream()
//                        .filter(order ->
//                                order.getStatus().equals(customFilter.getStatus())
//                        )
//                        .collect(Collectors.toList());
//            }
//        }

        if (customFilter != null) {
            query = query.stream()
                        .filter(order -> order.getStatus().equals(customFilter))
                        .collect(Collectors.toList());
        }

        return query;
    }
}

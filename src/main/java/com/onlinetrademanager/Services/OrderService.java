package com.onlinetrademanager.Services;

import com.onlinetrademanager.Exceptions.OrderNotFoundException;
import com.onlinetrademanager.Models.DeliveryCompany;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Models.Users.User;
import com.onlinetrademanager.Repositories.DeliveryCompaniesRepository;
import com.onlinetrademanager.Repositories.OrderRepository;
import com.onlinetrademanager.Repositories.StoreRepository;
import com.onlinetrademanager.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    public final OrderRepository orderRepository;
    public final DeliveryCompaniesRepository deliveryCompaniesRepository;
    public final StoreRepository storeRepository;
    public final UsersRepository usersRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, DeliveryCompaniesRepository deliveryCompaniesRepository, StoreRepository storeRepository, UsersRepository usersRepository){
        this.orderRepository = orderRepository;
        this.deliveryCompaniesRepository = deliveryCompaniesRepository;
        this.storeRepository = storeRepository;
        this.usersRepository = usersRepository;
    }

    public Order insertOrder(Order order){
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order){
        Order orderUpd = new Order(order);
        orderRepository.save(orderUpd);
        return orderUpd;
    }

    public void deleteOrderById(UUID id){
        orderRepository.deleteOrderById(id);
    }

    public void deleteOrder(Order order){
        orderRepository.delete(order);
    }

    public Order findOrderById(UUID id){
        return orderRepository.findOrderById(id).orElseThrow(()
                -> new OrderNotFoundException("Order " + id + "not found!"));
    }

    public List<Order> findAllOrdersByDeliveryCompanyId(UUID delCompId){
        DeliveryCompany deliveryCompany = deliveryCompaniesRepository.getById(delCompId);
        return orderRepository.findAllOrdersByDeliveryCompany(deliveryCompany);
    }

    public List<Order> findAllOrdersByUserId(UUID userId){
        User user = usersRepository.getById(userId);
        return orderRepository.findAllOrdersByUser(user);
    }

    public List<Order> findAllOrders(){ return orderRepository.findAll(); }

    public List<Order> findAllOrdersByStoreId(UUID storeId){
        Store store = storeRepository.findStoreById(storeId).orElseThrow(()
                -> new OrderNotFoundException("Store " + storeId + "not found!"));
        return orderRepository.findAllOrdersByStore(store);
    }

}

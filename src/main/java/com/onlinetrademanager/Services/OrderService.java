package com.onlinetrademanager.Services;

import com.onlinetrademanager.Exceptions.OrderNotFoundException;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Models.Users.User;
import com.onlinetrademanager.Repositories.OrderRepository;
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
    //public final StoreRepository storeRepository;
    public final UsersRepository usersRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UsersRepository usersRepository){
        this.orderRepository = orderRepository;
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

    public List<Order> findAllOrdersByStore(UUID id){
        //Store store = storeRepository.getUserById(id);
        return orderRepository.findAllOrdersByStore(new Store());
    }

    public List<Order> findAllOrdersByUser(UUID id){
        User user = usersRepository.getById(id);
        return orderRepository.findAllOrdersByUser(user);
    }

    public List<Order> findAllOrders(){ return orderRepository.findAll(); }

}

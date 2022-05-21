package com.onlinetrademanager.Controllers;


import com.onlinetrademanager.Models.Image;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Services.ImageService;
import com.onlinetrademanager.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(UUID id) {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/All_By_DeliveryCompany")
    public ResponseEntity<List<Order>> getAllOrdersByDeliveryCompanyId(UUID delCompId) {
        List<Order> orders = orderService.findAllOrdersByDeliveryCompanyId(delCompId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/All_By_Store")
    public ResponseEntity<List<Order>> getAllOrdersByStoreId(UUID storeId) {
        List<Order> orders = orderService.findAllOrdersByStoreId(storeId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/All_By_User")
    public ResponseEntity<List<Order>> getAllOrdersByUser(UUID id) {
        List<Order> orders = orderService.findAllOrdersByUserId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order newOrder = null;
        try {
            newOrder = orderService.insertOrder(order);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updateOrder = null;
        try {
            updateOrder = orderService.updateOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteOrder(@RequestParam UUID id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

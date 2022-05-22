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

    @GetMapping
    public ResponseEntity<Order> getOrder(@RequestParam  UUID id) {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/DeliveryCompany")
    public ResponseEntity<List<Order>> getAllOrdersByDeliveryCompanyId(@RequestParam UUID delCompId) {
        List<Order> orders = orderService.findAllOrdersByDeliveryCompanyId(delCompId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/Store")
    public ResponseEntity<List<Order>> getAllOrdersByStoreId(@RequestParam UUID storeId) {
        List<Order> orders = orderService.findAllOrdersByStoreId(storeId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/User")
    public ResponseEntity<List<Order>> getAllOrdersByUser(UUID userId) {
        List<Order> orders = orderService.findAllOrdersByUserId(userId);
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

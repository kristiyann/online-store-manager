package com.onlinetrademanager.Controllers;


import com.onlinetrademanager.DataTransferObjects.BatchChangeModel;
import com.onlinetrademanager.DataTransferObjects.Orders.OrderEdit;
import com.onlinetrademanager.DataTransferObjects.Orders.OrderInsert;
import com.onlinetrademanager.DataTransferObjects.Orders.OrderList;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Services.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Order")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService){
        this.ordersService = ordersService;
    }

    @GetMapping
    public ResponseEntity<OrderList> getOrder(@RequestParam  UUID id) {
        OrderList order = ordersService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<OrderList>> getAllOrders() {
        List<OrderList> orders = ordersService.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/DeliveryCompany")
    public ResponseEntity<List<OrderList>> getAllOrdersByDeliveryCompanyId(@RequestParam UUID delCompId) {
        List<OrderList> orders = ordersService.findAllOrdersByDeliveryCompanyId(delCompId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

//    @GetMapping("/Store")
//    public ResponseEntity<List<Order>> getAllOrdersByStoreId(@RequestParam UUID storeId) {
//        List<Order> orders = ordersService.findAllOrdersByStoreId(storeId);
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }

    @GetMapping("/Client")
    public ResponseEntity<List<OrderList>> getAllOrdersByClient(UUID clientId) {
        List<OrderList> orders = ordersService.findAllOrdersByClient(clientId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> addOrder(@RequestBody OrderInsert order) {
        UUID id = null;
        try {
            id = ordersService.insertOrder(order);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<OrderList> updateOrder(@RequestBody OrderEdit order) {
        OrderList updateOrder = null;
        try {
            updateOrder = ordersService.updateOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

//    @PutMapping("/BatchChangeItems")
//    public ResponseEntity<OrderList> batchChangeItems(@RequestBody BatchChangeModel batchChangeModel) {
//        OrderList updateOrder = null;
//        try {
//            updateOrder = ordersService.batchChangeItems(batchChangeModel);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
//    }
    // ! Not going to be used

    @DeleteMapping()
    public ResponseEntity<?> deleteOrder(@RequestParam UUID id) {
        ordersService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

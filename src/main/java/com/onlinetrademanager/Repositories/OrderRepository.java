package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.DeliveryCompany;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findOrderById(UUID id);

    void deleteOrderById(UUID id);

    List<Order> findAllOrdersByUser(User user);

    List<Order> findAllOrdersByDeliveryCompany(DeliveryCompany deliveryCompany);

    List<Order> findAllOrdersByStore(Store store);
}

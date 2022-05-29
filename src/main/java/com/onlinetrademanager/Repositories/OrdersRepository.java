package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.DeliveryCompany;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findOrderById(UUID id);

    void deleteOrderById(UUID id);

    List<Order> findAllOrdersByClient(Client client);

    List<Order> findAllOrdersByDeliveryCompany(DeliveryCompany deliveryCompany);

//    List<Order> findAllOrdersByStore(Store store);
}

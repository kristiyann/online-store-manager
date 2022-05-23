package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Order;
import com.onlinetrademanager.Models.XRefOrdersItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface XRefOrdersItemsRepository extends JpaRepository<XRefOrdersItems, UUID> {
    Optional<XRefOrdersItems> getByOrderAndItem(Order order, Item item);
}

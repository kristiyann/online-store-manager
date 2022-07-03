package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findItemById(UUID id);

    void deleteItemById(UUID id);

    List<Item> findAllItemsByStore(Store store);

    List<Item> findAllItemsByDeleted(boolean deleted);
}

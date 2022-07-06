package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemsRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findItemById(UUID id);

    void deleteItemById(UUID id);

    List<Item> findAllItemsByStore(Store store);

    @Query("select i from Item i where i.id in :ids" )
    List<Item> findItemsByIds(List<UUID> ids);
}

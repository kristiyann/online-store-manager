package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Users.Client;
import com.onlinetrademanager.Models.XRefClientsItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface XRefClientsItemsRepository extends JpaRepository<XRefClientsItems, UUID> {
    Optional<XRefClientsItems> getByClientAndItem(Client client, Item item);
}

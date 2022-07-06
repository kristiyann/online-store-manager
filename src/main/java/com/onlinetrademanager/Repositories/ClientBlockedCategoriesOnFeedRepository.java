package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Enums.Item.ItemCategory;
import com.onlinetrademanager.Models.ClientBlockedCategoriesOnFeed;
import com.onlinetrademanager.Models.Users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientBlockedCategoriesOnFeedRepository extends JpaRepository<ClientBlockedCategoriesOnFeed, UUID>  {
    Optional<ClientBlockedCategoriesOnFeed> findByClientAndCategory(Client client, ItemCategory category);
}

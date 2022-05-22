package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoresRepository extends JpaRepository<Store, UUID> {
    Optional<Store> findStoreById(UUID id);

    void deleteStoreById(UUID id);

    List<Store> findAllStoresByUser(User user);
}

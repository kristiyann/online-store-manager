package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.DataTransferObjects.Clients.ClientList;
import com.onlinetrademanager.Models.Users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientsRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findClientById(UUID id);
}

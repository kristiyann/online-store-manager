package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientsRepository extends JpaRepository<User, UUID> {
}

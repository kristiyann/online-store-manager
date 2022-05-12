package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID id);

    Optional<User> findUserByEmail(String email);
}

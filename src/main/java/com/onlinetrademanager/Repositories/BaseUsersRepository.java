package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.Users.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BaseUsersRepository extends JpaRepository<BaseUser, UUID> {
    Optional<BaseUser> findBaseUserByEmail(String email);
}

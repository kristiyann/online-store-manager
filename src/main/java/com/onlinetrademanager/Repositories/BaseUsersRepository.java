package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.Users.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BaseUsersRepository extends JpaRepository<BaseUser, UUID> {
    Optional<BaseUser> findBaseUserByEmail(String email);
}

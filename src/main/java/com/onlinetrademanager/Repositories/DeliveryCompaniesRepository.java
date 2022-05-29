package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.DeliveryCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryCompaniesRepository extends JpaRepository<DeliveryCompany, UUID> {
    void deleteDeliveryCompanyById(UUID id);

    Optional<DeliveryCompany> findDeliveryCompanyById(UUID deliveryCompanyId);
}

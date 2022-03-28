package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.DeliveryCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryCompaniesRepository extends JpaRepository<DeliveryCompany, UUID> {
    void deleteDeliveryCompanyById(UUID id);
}

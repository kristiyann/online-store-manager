package com.onlinetrademanager.Repositories;

import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Optional<Sale> findSaleById(UUID id);

    void deleteSaleById(UUID id);

    // Optional<Sale> findSaleByItem(Item item);

    List<Sale> findAllSalesByDeleted(boolean deleted);

}

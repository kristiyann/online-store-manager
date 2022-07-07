package com.onlinetrademanager.Services;


import com.onlinetrademanager.Exceptions.ItemNotFoundException;
import com.onlinetrademanager.Exceptions.SaleNotFoundException;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Sale;
import com.onlinetrademanager.Repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleService {

    public final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository){
        this.saleRepository = saleRepository;
    }

    public Sale insertSale(Sale sale){
        sale.setDeleted(false);
        saleRepository.save(sale);
        return sale;
    }

    public Sale updateSale(Sale sale){
        Sale saleUpd = new Sale(sale);
        saleRepository.save(saleUpd);
        return saleUpd;
    }

    public Sale updateSaleDeleted(Sale sale)
    {
        Sale saleUpd = new Sale(sale);
        saleUpd.setDeleted(true);
        saleRepository.save(saleUpd);
        return saleUpd;
    }

    public void deleteSale(Sale sale){
        saleRepository.delete(sale);
    }

    public void deleteSaleById(UUID id){
        saleRepository.deleteSaleById(id);
    }

    public Sale findSaleById(UUID id){
        return saleRepository.findSaleById(id).orElseThrow(
                () -> new SaleNotFoundException("Sale " + id + "not found!")
        );
    }

    public Sale findSaleByItem(Item item){
        return saleRepository.findSaleByItem(item).orElseThrow(
                () -> new SaleNotFoundException("Item " + item.getId() + "does not have Sale object attached!")
        );
    }

    public List<Sale> findAllSales(){
        return saleRepository.findAll().stream().filter(a -> !a.isDeleted()).collect(Collectors.toList());
    }

    /*
    public List<Sale> findAllSalesByDeleted(boolean deleted){
        return saleRepository.findAllSalesByDeleted(deleted);
    }
    */
}

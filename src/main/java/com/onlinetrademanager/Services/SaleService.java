package com.onlinetrademanager.Services;


import com.onlinetrademanager.DataTransferObjects.Sales.SaleEdit;
import com.onlinetrademanager.DataTransferObjects.Sales.SaleList;
import com.onlinetrademanager.Exceptions.SaleNotFoundException;
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

    public Sale insertSale(SaleEdit sale){
        Sale insert = convertEditToDbObj(sale);
        insert.setDeleted(false);
        saleRepository.save(insert);
        return insert;
    }

    public Sale updateSale(SaleEdit sale){
        Sale saleUpd = convertEditToDbObj(sale);
        saleRepository.save(saleUpd);
        return saleUpd;
    }

    public void updateSaleDeleted(UUID id)
    {
        Sale dbObj = saleRepository.findSaleById(id)
                .stream()
                .filter(a -> !a.isDeleted())
                .findFirst()
                .orElseThrow(() -> new SaleNotFoundException("Sale " + id + "not found!"));

        dbObj.setDeleted(true);
        saleRepository.save(dbObj);
    }

    public void deleteSale(Sale sale){
        saleRepository.delete(sale);
    }

    public void deleteSaleById(UUID id){
        saleRepository.deleteSaleById(id);
    }

    public SaleList findSaleById(UUID id){
        return saleRepository.findSaleById(id)
                .stream()
                .filter(a -> !a.isDeleted())
                .map(this::convertDbObjToList)
                .findFirst()
                .orElseThrow(
                () -> new SaleNotFoundException("Sale " + id + "not found!")
        );
    }

//    public Sale findSaleByItem(Item item){
//        return saleRepository.findSaleByItem(item).orElseThrow(
//                () -> new SaleNotFoundException("Item " + item.getId() + "does not have Sale object attached!")
//        );
//    }

    public List<SaleList> findAllSales(){
        return saleRepository.findAll()
                .stream()
                .filter(a -> !a.isDeleted())
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    //#region Helper methods

    private SaleList convertDbObjToList(Sale sale) {
        SaleList saleList = new SaleList(sale.getId(), sale.getStartDate(), sale.getEndDate(), sale.getSalePercentage());
        return saleList;
    }

    private Sale convertEditToDbObj(SaleEdit saleEdit) {
        Sale sale = new Sale();

        sale.setSalePercentage(saleEdit.getSalePercentage());
        sale.setEndDate(saleEdit.getEndDate());
        sale.setId(saleEdit.getId());
        sale.setStartDate(saleEdit.getStartDate());

        return sale;
    }

}

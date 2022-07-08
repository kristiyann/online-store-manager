package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.DataTransferObjects.Sales.SaleEdit;
import com.onlinetrademanager.DataTransferObjects.Sales.SaleList;
import com.onlinetrademanager.Models.Sale;
import com.onlinetrademanager.Services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/Sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<SaleList> getSale(@RequestParam UUID id) {
        SaleList sale = saleService.findSaleById(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<SaleList>> getAllSales() {
        List<SaleList> sales = saleService.findAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    /*
    @GetMapping("/All_By_Deleted")
    public ResponseEntity<List<Sale>> getAllSalesByDeleted() {
        List<Sale> sales = saleService.findAllSalesByDeleted(false);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
     */

    @PostMapping()
    public ResponseEntity<Sale> addSale(@RequestBody SaleEdit sale) {
        Sale newSale = saleService.insertSale(sale);
        return new ResponseEntity<>(newSale, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Sale> updateSale(@RequestBody SaleEdit sale) {
        Sale updateSale = null;
        try {
            updateSale = saleService.updateSale(sale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateSale, HttpStatus.OK);
    }

    @PutMapping("/Delete")
    public ResponseEntity<Boolean> updateSaleDeleted(@RequestParam UUID id) {
        saleService.updateSaleDeleted(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

//    @DeleteMapping()
//    public ResponseEntity<?> deleteSale(@RequestParam UUID id) {
//        saleService.deleteSaleById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}


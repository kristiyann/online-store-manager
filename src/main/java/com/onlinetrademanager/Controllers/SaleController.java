package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.Models.Image;
import com.onlinetrademanager.Models.Item;
import com.onlinetrademanager.Models.Sale;
import com.onlinetrademanager.Services.ItemService;
import com.onlinetrademanager.Services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSale(UUID id) {
        Sale sale = saleService.findSaleById(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping("/all_sales")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.findAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Sale> addSale(@RequestBody Sale sale) {
        Sale newSale = saleService.insertSale(sale);
        return new ResponseEntity<>(newSale, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Sale> updateSale(@RequestBody Sale sale) {
        Sale updateSale = null;
        try {
            updateSale = saleService.updateSale(sale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateSale, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable("id") UUID id) {
        saleService.deleteSaleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.DataTransferObjects.DeliveryCompanies.DeliveryCompanyEdit;
import com.onlinetrademanager.Models.DeliveryCompany;
import com.onlinetrademanager.Services.DeliveryCompaniesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controllers are the most exposed layer of the MVC architecture which is used for creating this application.
 * With them, we can access data in the database and fetch, add, modify, or delete it.
 * Controllers create addresses which are used by the front-end part of the application.
 * These addresses follow the format: http://localhost:8080/api/{table name}.
 * Spring applications run on the 8080 port by default.
 * Controllers' addresses redirect and trigger methods from the Service layer
 * We can pass parameters in a couple ways:
 * @PathVariable - requires the parameters to be set in the address string
 * @RequestBody - requires the parameters to be set in the JSON body
 * The current controller is used for operations on Delivery Companies.
 * **/

@RestController
@RequestMapping("/api/DeliveryCompany")
public class DeliveryCompaniesController {
    private final DeliveryCompaniesService deliveryCompaniesService;

    public DeliveryCompaniesController(DeliveryCompaniesService deliveryCompaniesService) {
        this.deliveryCompaniesService = deliveryCompaniesService;
    }

    @GetMapping("/All")
    public ResponseEntity<List<DeliveryCompany>> getDeliveryCompanyList() {
        List<DeliveryCompany> deliveryCompanies = deliveryCompaniesService.findAllDeliveryCompanies();
        return new ResponseEntity<>(deliveryCompanies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> insertDeliveryCompany(@RequestBody DeliveryCompany deliveryCompany) {
        UUID deliveryCompanyId = deliveryCompaniesService.insertDeliveryCompany(deliveryCompany);
        return new ResponseEntity<>(deliveryCompanyId, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DeliveryCompany> updateDeliveryCompany(@RequestBody DeliveryCompanyEdit deliveryCompanyEdit) {
        DeliveryCompany deliveryCompany = deliveryCompaniesService.updateDeliveryCompany(deliveryCompanyEdit);
        return new ResponseEntity<>(deliveryCompany, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDeliveryCompany(@RequestParam UUID id) {
        deliveryCompaniesService.deleteDeliveryCompany(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

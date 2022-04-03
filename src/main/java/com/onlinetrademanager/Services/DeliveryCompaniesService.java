package com.onlinetrademanager.Services;


import com.onlinetrademanager.DataTransferObjects.DeliveryCompanies.DeliveryCompanyEdit;
import com.onlinetrademanager.Models.DeliveryCompany;
import com.onlinetrademanager.Repositories.DeliveryCompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Services are a layer in the MVC design architecture which is used for building this application.
 * They describe the behaviour of models.
 * The current service is used to give functionality to objects of type Delivery Company.
 * **/

@Service
@Transactional
public class DeliveryCompaniesService {
    private final DeliveryCompaniesRepository deliveryCompaniesRepository;

    @Autowired
    public DeliveryCompaniesService(DeliveryCompaniesRepository deliveryCompaniesRepository) {
        this.deliveryCompaniesRepository = deliveryCompaniesRepository;
    }

    public List<DeliveryCompany> findAllDeliveryCompanies() {
        return deliveryCompaniesRepository.findAll();
    }

    public UUID insertDeliveryCompany(DeliveryCompany deliveryCompany) {
        deliveryCompaniesRepository.save(deliveryCompany);
        return deliveryCompany.getId();
    }

    public DeliveryCompany updateDeliveryCompany(DeliveryCompanyEdit deliveryCompanyEdit) {
        DeliveryCompany deliveryCompany = convertDeliveryCompanyEditToDbObj(deliveryCompanyEdit);
        deliveryCompaniesRepository.save(deliveryCompany);
        return deliveryCompany;
    }

    public void deleteDeliveryCompany(UUID id) {
        deliveryCompaniesRepository.deleteDeliveryCompanyById(id);
    }

    private DeliveryCompany convertDeliveryCompanyEditToDbObj(DeliveryCompanyEdit deliveryCompanyEdit) {
        DeliveryCompany deliveryCompany = new DeliveryCompany();

        deliveryCompany.setId(deliveryCompanyEdit.getId());
        deliveryCompany.setName(deliveryCompanyEdit.getName());
        deliveryCompany.setDeliveryFee(deliveryCompanyEdit.getDeliveryFee());

        return deliveryCompany;
    }
}

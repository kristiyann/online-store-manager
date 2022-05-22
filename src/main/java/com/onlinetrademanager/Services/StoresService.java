package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.Stores.StoreEdit;
import com.onlinetrademanager.Exceptions.StoreNotFoundException;
import com.onlinetrademanager.Exceptions.UserNotFoundException;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Models.Users.User;
import com.onlinetrademanager.Repositories.StoresRepository;
import com.onlinetrademanager.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class StoresService {
    public final StoresRepository storesRepository;
    public final UsersRepository usersRepository;

    @Autowired
    public StoresService(StoresRepository storesRepository, UsersRepository usersRepository){
        this.storesRepository = storesRepository;
        this.usersRepository = usersRepository;
    }

    public UUID insertStore(StoreEdit store){
        Store dbObj = convertEditToDbObj(store);
        storesRepository.save(dbObj);
        return dbObj.getId();
    }

    public Store updateStore(StoreEdit store){
        Store storeUpd = convertEditToDbObj(store);
        storesRepository.save(storeUpd);
        return storeUpd;
    }

    public void deleteStore(Store store){
        storesRepository.delete(store);
    }

    public void deleteStoreById(UUID id){
        storesRepository.deleteStoreById(id);
    }

    public Store findStoreById(UUID id){
        return storesRepository.findStoreById(id).orElseThrow(()
                -> new StoreNotFoundException("Store " + id + "not found!"));
    }

    public List<Store> findAllStores(){
        return storesRepository.findAll();
    }

    //#region Helper methods

    public Store convertEditToDbObj(StoreEdit editModel) {
        Store store = new Store();

        User user = usersRepository.findUserById(editModel.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Store " + editModel.getUserId() + "not found!"));

        store.setId(editModel.getId());
        store.setActive(editModel.getActive());
        store.setAddress(editModel.getAddress());
        store.setUser(user);

        return store;
    }
}

package com.onlinetrademanager.Services;

import com.onlinetrademanager.Exceptions.StoreNotFoundException;
import com.onlinetrademanager.Models.Store;
import com.onlinetrademanager.Models.Users.User;
import com.onlinetrademanager.Repositories.StoreRepository;
import com.onlinetrademanager.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StoreService {

    public final StoreRepository storeRepository;
    public final UsersRepository userRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository, UsersRepository userRepository){
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
    }

    public Store insertStore(Store store){
        storeRepository.save(store);
        return store;
    }

    public Store updateStore(Store store){
        Store storeUpd = new Store(store);
        storeRepository.save(storeUpd);
        return storeUpd;
    }

    public void deleteStore(Store store){
        storeRepository.delete(store);
    }

    public void deleteStoreById(UUID id){
        storeRepository.deleteStoreById(id);
    }

    public Store findStoreById(UUID id){
        return storeRepository.findStoreById(id).orElseThrow(()
                -> new StoreNotFoundException("Store " + id + "not found!"));
    }

    public List<Store> findAllStores(){
        return storeRepository.findAll();
    }

}

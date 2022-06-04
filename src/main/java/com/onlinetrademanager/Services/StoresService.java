package com.onlinetrademanager.Services;

import com.onlinetrademanager.DataTransferObjects.Stores.StoreEdit;
import com.onlinetrademanager.DataTransferObjects.Stores.StoreList;
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
import java.util.UUID;
import java.util.stream.Collectors;

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
        Store dbObj = this.convertEditToDbObj(store);
        storesRepository.save(dbObj);
        return dbObj.getId();
    }

    public Store updateStore(StoreEdit store){
        Store storeUpd = this.convertEditToDbObj(store);
        storesRepository.save(storeUpd);
        return storeUpd;
    }

    public void deleteStore(Store store){
        storesRepository.delete(store);
    }

    public void deleteStoreById(UUID id){
        storesRepository.deleteStoreById(id);
    }

    public StoreList findStoreById(UUID id){
        return storesRepository.findStoreById(id)
                .stream()
                .map(this::convertDbObjToList)
                .findFirst()
                .orElseThrow(()
                -> new StoreNotFoundException("Store " + id + "not found!"));
    }

    public List<StoreList> findAllStoresByUser(UUID userId){
        User user = usersRepository.findUserById(userId).orElseThrow(()
                -> new UserNotFoundException("User " + userId + "not found!"));

        return storesRepository.findAllStoresByUser(user)
                .stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    public List<StoreList> findAllStores(){
        return storesRepository.findAll()
                .stream()
                .map(this::convertDbObjToList)
                .collect(Collectors.toList());
    }

    //#region Helper methods

    public Store convertEditToDbObj(StoreEdit editModel) {
        Store store = new Store();

        User user = usersRepository.findUserById(editModel.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User " + editModel.getUserId() + "not found!"));

        store.setId(editModel.getId());
        store.setActive(editModel.getActive());
        store.setAddress(editModel.getAddress());
        store.setUser(user);
        store.setName(editModel.getName());

        return store;
    }

    public StoreList convertDbObjToList(Store dbObj) {
        StoreList store = new StoreList();

        store.setId(dbObj.getId());
        store.setActive(dbObj.getActive());
        store.setAddress(dbObj.getAddress());
        store.setUserId(dbObj.getUser().getId());
        store.setName(dbObj.getName());

        return store;
    }
}

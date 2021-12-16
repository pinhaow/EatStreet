package com.odhiambopaul.demo.services.imp;

import com.odhiambopaul.demo.model.Store;
import com.odhiambopaul.demo.repositories.StoreRepository;
import com.odhiambopaul.demo.services.StoreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getStores() {
        List<Store> stores = new ArrayList<>();
        storeRepository.findAll().forEach(stores::add);
        return stores;
    }

    @Override
    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId).get();
    }

    @Override
    public Store insert(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public void updateStore(Store store) {
        Store storeFromDb = storeRepository.findById(store.getId()).get();
        System.out.println(storeFromDb.toString());
        storeFromDb.setName(store.getName());
        storeFromDb.setAddress(store.getAddress());
        storeFromDb.setPhone(store.getPhone());
        storeRepository.save(storeFromDb);
    }

    @Override
    public void deleteStore(Long storeId) {
        storeRepository.deleteById(storeId);
    }

    @Override
    public Store getStoreByUserId(Long userId) {
        for(Store store : storeRepository.findAll()){
            if (store.getOwnerid().equals(userId)) return store;
        }
        return null;
    }
}

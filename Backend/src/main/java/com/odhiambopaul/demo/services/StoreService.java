package com.odhiambopaul.demo.services;

import com.odhiambopaul.demo.model.Store;

import java.util.List;

public interface StoreService {
    List<Store> getStores();

    Store getStoreById(Long storeId);

    Store insert(Store store);

    void updateStore(Store store);

    void deleteStore(Long storeId);

    Store getStoreByUserId(Long userId);
}

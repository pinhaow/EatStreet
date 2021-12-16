package com.odhiambopaul.demo.bootstrap;

import com.odhiambopaul.demo.repositories.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StoreLoader implements CommandLineRunner {
    public final StoreRepository storeRepository;

    public StoreLoader(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadStores();
    }

    private void loadStores() {
        if (storeRepository.count() == 0) {
            System.out.println("Sample Store Loaded");
        }
    }
}

package com.odhiambopaul.demo.bootstrap;

import com.odhiambopaul.demo.repositories.CartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CartLoader implements CommandLineRunner {
    public final CartRepository cartRepository;

    public CartLoader(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadItems();
    }

    private void loadItems() {
        if (cartRepository.count() == 0) {
            System.out.println("Sample Cart Loaded");
        }
    }
}

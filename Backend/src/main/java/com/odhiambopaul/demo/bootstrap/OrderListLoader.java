package com.odhiambopaul.demo.bootstrap;

import com.odhiambopaul.demo.repositories.OrderListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OrderListLoader implements CommandLineRunner {
    public final OrderListRepository orderlistRepository;

    public OrderListLoader(OrderListRepository orderlistRepository) {
        this.orderlistRepository = orderlistRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadItems();
    }

    private void loadItems() {
        if (orderlistRepository.count() == 0) {
            System.out.println("Sample OrderList Loaded");
        }
    }
}

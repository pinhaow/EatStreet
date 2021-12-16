package com.odhiambopaul.demo.bootstrap;

import com.odhiambopaul.demo.repositories.OrderDetailRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailLoader implements CommandLineRunner {
    public final OrderDetailRepository orderdetailRepository;

    public OrderDetailLoader(OrderDetailRepository orderdetailRepository) {
        this.orderdetailRepository = orderdetailRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadOrderDetails();
    }

    private void loadOrderDetails() {
        if (orderdetailRepository.count() == 0) {
            System.out.println("Sample OrderDetail Loaded");
        }
    }
}

package com.odhiambopaul.demo.bootstrap;

import com.odhiambopaul.demo.repositories.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ItemLoader implements CommandLineRunner {
    public final ItemRepository itemRepository;

    public ItemLoader(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadItems();
    }

    private void loadItems() {
        if (itemRepository.count() == 0) {
            System.out.println("Sample Item Loaded");
        }
    }
}

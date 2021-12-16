package com.odhiambopaul.demo.services.imp;

import com.odhiambopaul.demo.model.Item;
import com.odhiambopaul.demo.repositories.ItemRepository;
import com.odhiambopaul.demo.services.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems(Long storeId) {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(item -> {
            if (item.getStoreid().equals(storeId)) items.add(item);
        });
        return items;
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Override
    public Item insert(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void updateItem(Item item) {
        Item itemFromDb = itemRepository.findById(item.getId()).get();
        System.out.println(itemFromDb.toString());
        itemFromDb.setName(item.getName());
        itemFromDb.setQuantity(item.getQuantity());
        itemFromDb.setPrice(item.getPrice());
        itemRepository.save(itemFromDb);
    }

    @Override
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}

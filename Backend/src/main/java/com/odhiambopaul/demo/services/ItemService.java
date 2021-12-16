package com.odhiambopaul.demo.services;

import com.odhiambopaul.demo.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems(Long storeId);

    Item getItemById(Long itemId);

    Item insert(Item item);

    void updateItem(Item item);

    void deleteItem(Long itemId);
}

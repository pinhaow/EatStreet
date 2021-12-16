package com.odhiambopaul.demo.controllers;

import com.odhiambopaul.demo.model.Item;
import com.odhiambopaul.demo.model.Store;
import com.odhiambopaul.demo.model.Todo;
import com.odhiambopaul.demo.services.ItemService;
import com.odhiambopaul.demo.services.StoreService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    ItemService itemService;
    StoreService storeService;

    public ItemController(ItemService itemService, StoreService storeService) {
        this.itemService = itemService;
        this.storeService = storeService;
    }

    @GetMapping("/all/{storeId}")
    public ResponseEntity<List<Item>> getAllItems(@PathVariable Long storeId) {
        List<Item> items = itemService.getItems(storeId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping({"/{itemId}"})
    public ResponseEntity<Item> getItem(@PathVariable Long itemId) {
        return new ResponseEntity<>(itemService.getItemById(itemId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item item1 = itemService.insert(item);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("item", "/api/v1/item/" + item1.getId().toString());
        return new ResponseEntity<>(item1, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping({"/{userId}"})
    public ResponseEntity<Item> addItem(@PathVariable Long userId, @RequestBody Item item) {
        Store storeToUpdate = storeService.getStoreByUserId(userId);
        if (storeToUpdate == null )
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        item.setStoreid(storeToUpdate.getId());
        Item item1 = itemService.insert(item);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("item", "/api/v1/item/" + item1.getId().toString());
        return new ResponseEntity<>(item1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        itemService.updateItem(item);
        return new ResponseEntity<>(itemService.getItemById(item.getId()), HttpStatus.OK);
    }

    @DeleteMapping({"/{itemId}"})
    public ResponseEntity<Todo> deleteTodo(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

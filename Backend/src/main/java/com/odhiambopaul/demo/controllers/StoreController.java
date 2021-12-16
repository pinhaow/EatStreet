package com.odhiambopaul.demo.controllers;

import com.odhiambopaul.demo.model.Item;
import com.odhiambopaul.demo.model.Todo;
import com.odhiambopaul.demo.model.Store;
import com.odhiambopaul.demo.services.ItemService;
import com.odhiambopaul.demo.services.StoreService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {
    StoreService storeService;
    ItemService itemService;

    public StoreController(StoreService storeService, ItemService itemService) {
        this.storeService = storeService;
        this.itemService = itemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping({"/{storeId}"})
    public ResponseEntity<Store> getStore(@PathVariable Long storeId) {
        return new ResponseEntity<>(storeService.getStoreById(storeId), HttpStatus.OK);
    }

    @GetMapping({"/items/{userId}"})
    public ResponseEntity<List<Item>> getStoreItems(@PathVariable Long userId) {
        Store myStore = storeService.getStoreByUserId(userId);
        List<Item> items = itemService.getItems(myStore.getId());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping({"/mine/{userId}"})
    public ResponseEntity<Store> getMyStore(@PathVariable Long userId) {
        Store myStore = storeService.getStoreByUserId(userId);
        if (myStore == null) {
            myStore = new Store();
            myStore.setName("");
            myStore.setPhone("");
            myStore.setAddress("");
            myStore.setOwnerid(userId);
        }
        return new ResponseEntity<>(myStore, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Store> addStore(@RequestBody Store store) {
        Store store1 = storeService.insert(store);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("store", "/api/v1/store/" + store1.getId().toString());
        return new ResponseEntity<>(store1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Store> updateStore(@RequestBody Store store) {
        Store storeToUpdate = storeService.getStoreByUserId(store.getOwnerid());
        if (storeToUpdate == null )
            return addStore(store);
        store.setId(storeToUpdate.getId());
        storeService.updateStore(store);
        return new ResponseEntity<>(storeService.getStoreById(store.getId()), HttpStatus.OK);
    }

    @DeleteMapping({"/{storeId}"})
    public ResponseEntity<Todo> deleteTodo(@PathVariable("storeId") Long storeId) {
        storeService.deleteStore(storeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

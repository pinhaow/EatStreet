package com.odhiambopaul.demo.controllers;

import com.odhiambopaul.demo.model.*;
import com.odhiambopaul.demo.services.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    CartService cartService;
    OrderListService orderListService;
    StoreService storeService;
    UserService userService;
    ItemService itemService;
    OrderDetailService orderDetailService;

    public CartController(CartService cartService, OrderListService orderListService,
                          StoreService storeService, UserService userService, ItemService itemService,
                          OrderDetailService orderDetailService
    ) {
        this.cartService = cartService;
        this.orderListService = orderListService;
        this.storeService = storeService;
        this.userService = userService;
        this.itemService = itemService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Cart>> getAllCarts(@PathVariable Long userId) {
        List<Cart> carts = cartService.getCarts(userId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping({"/{cartId}"})
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        Cart cart1 = cartService.insert(cart);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("cart", "/api/v1/cart/" + cart1.getId().toString());
        return new ResponseEntity<>(cart1, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/confirm/{userId}")
    public ResponseEntity<List<Cart>> confirmCart(@PathVariable Long userId) {
        List<Cart> carts = cartService.getCarts(userId);
        if (!carts.isEmpty()){
            Long storeId = itemService.getItemById(carts.get(0).getItemid()).getStoreid();
            Store store = storeService.getStoreById(storeId);
            User user = userService.getUserById(userId);

            OrderList orderList = new OrderList();
            orderList.setUserid(userId);
            orderList.setUsercontact(user.getMobilephone());
            orderList.setDriverid(0L);
            orderList.setDrivername("");
            orderList.setDrivercontact("");
            orderList.setStoreid(store.getId());
            orderList.setStorename(store.getName());
            orderList.setPickup(store.getAddress());
            orderList.setUserid(user.getId());
            orderList.setTerminal(user.getAddress());
            orderList.setStatus("INIT");
            OrderList newOrderList = orderListService.insert(orderList);
            carts.forEach(cart -> {
                Item item = itemService.getItemById(cart.getItemid());
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderlistid(newOrderList.getId());
                orderDetail.setItemname(item.getName());
                orderDetail.setItemprice(item.getPrice());
                orderDetail.setQuantity(cart.getQuantity());
                orderDetailService.insert(orderDetail);
                cartService.deleteCart(cart.getId());
            });
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(carts, httpHeaders, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        cartService.updateCart(cart);
        return new ResponseEntity<>(cartService.getCartById(cart.getId()), HttpStatus.OK);
    }

    @DeleteMapping({"/{cartId}"})
    public ResponseEntity<Cart> deleteTodo(@PathVariable("cartId") Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}

package com.odhiambopaul.demo.services;

import com.odhiambopaul.demo.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCarts(Long userId);

    Cart getCartById(Long cartId);

    Cart insert(Cart cart);

    void updateCart(Cart cart);

    void deleteCart(Long cartId);
}

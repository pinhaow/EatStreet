package com.odhiambopaul.demo.services.imp;

import com.odhiambopaul.demo.model.Cart;
import com.odhiambopaul.demo.repositories.CartRepository;
import com.odhiambopaul.demo.services.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getCarts(Long userId) {
        List<Cart> carts = new ArrayList<>();
        cartRepository.findAll().forEach(cart -> {
            if (cart.getUserid().equals(userId)) carts.add(cart);
        });
        return carts;
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    @Override
    public Cart insert(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        Cart cartFromDb = cartRepository.findById(cart.getId()).get();
        System.out.println(cartFromDb.toString());
        cartFromDb.setItemname(cart.getItemname());
        cartFromDb.setQuantity(cart.getQuantity());
        cartFromDb.setItemprice(cart.getItemprice());
        cartRepository.save(cartFromDb);
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}

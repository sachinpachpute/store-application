package com.sp.spring.orderservice.service.impl;

import com.sp.spring.orderservice.entity.Cart;
import com.sp.spring.orderservice.entity.CartItem;
import com.sp.spring.orderservice.repository.CartRepository;
import com.sp.spring.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCart() {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();*/
        String userName = "sachin_rait";

        Cart cartByUserName = cartRepository.findCartByUserName(userName);

        synchronized (CartServiceImpl.class){
            if (cartByUserName == null) {
                createCart();
                cartByUserName = cartRepository.findCartByUserName(userName);
            }
        }

        double totalPrice = cartByUserName.getCartItems()
                .stream()
                .mapToDouble(CartItem::getExtendedPrice)
                .sum();

        cartByUserName.setTotalPrice(totalPrice);

        return cartByUserName;
    }

    @Override
    public Cart getCartByCartId(String cartId) {
        return null;
    }

    @Override
    public String createCart() {
        return null;
    }

    @Override
    public Cart getCartByUserName(String userName) {
        return null;
    }
}

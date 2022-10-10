package com.sp.spring.orderservice.service;

import com.sp.spring.orderservice.entity.CartItem;
import com.sp.spring.orderservice.web.CartItemRequest;
import org.springframework.stereotype.Service;

public interface CartItemService {
    void addCartItem(CartItemRequest cartItemRequest);
    void removeCartItem(String cartItemId);
    CartItem getCartItem(String cartItemId);
    void removeAllCartItems(String cartId);
}

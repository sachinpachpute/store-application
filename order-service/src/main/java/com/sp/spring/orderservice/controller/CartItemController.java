package com.sp.spring.orderservice.controller;

import com.sp.spring.orderservice.web.CartItemRequest;
import com.sp.spring.orderservice.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @PostMapping("/cart/cartItem")
    @ResponseStatus(value = HttpStatus.OK)
    public void addCartItem(@RequestBody CartItemRequest cartItemRequest) {
        System.out.println("Testing 123....................................");
        System.out.println("Testing 123....................................");
        System.out.println("Testing 123....................................");
        System.out.println("Testing 123....................................");
        System.out.println("Testing 123....................................");
        cartItemService.addCartItem(cartItemRequest);
    }

    @DeleteMapping("/cart/cartItem/{cartItemId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartItem(@PathVariable(value = "cartItemId") String cartItemId) {
        cartItemService.removeCartItem(cartItemId);
    }

    @DeleteMapping("/cart/cartItem")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeAllCartItems(@RequestParam(value = "cartId") String cartId) {
        System.out.println("Testing 234...............................................");
        System.out.println("Testing 234...............................................");
        System.out.println("Testing 234...............................................");
        System.out.println("Testing 234...............................................");
        cartItemService.removeAllCartItems(cartId);
    }
}

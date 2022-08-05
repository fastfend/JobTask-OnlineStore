package com.cdprojekt.store.service.order.controller;

import com.cdprojekt.store.service.order.service.CartService;
import com.cdprojekt.store.service.order.service.OrderService;
import com.cdprojekt.store.shared.order.Cart;
import com.cdprojekt.store.shared.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;

    @PostMapping
    public Order saveOrder(@RequestParam UUID cartId) {
        Cart cart = cartService.getCart(cartId);
        if(cart.getItems().isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return orderService.createOrder(cart);
    }
}

package com.cdprojekt.store.service.order.controller;

import com.cdprojekt.store.service.order.service.CartService;
import com.cdprojekt.store.shared.order.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{cartId}")
    public Cart get(@PathVariable UUID cartId) {
        return cartService.getCart(cartId);
    }

    @PutMapping("/{cartId}")
    public Cart set(@PathVariable UUID cartId,
                    @RequestParam UUID productId,
                    @RequestParam int count) {
        return cartService
                .setItemCount(cartId, productId, count)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{cartId}")
    public void delete(@PathVariable UUID cartId) {
        cartService.deleteCart(cartId);
    }

}

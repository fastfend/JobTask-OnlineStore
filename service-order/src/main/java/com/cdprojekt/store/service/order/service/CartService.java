package com.cdprojekt.store.service.order.service;

import com.cdprojekt.store.service.order.client.GameClient;
import com.cdprojekt.store.service.order.repository.CartRepository;
import com.cdprojekt.store.shared.order.Cart;
import com.cdprojekt.store.shared.order.CartItem;
import com.cdprojekt.store.shared.game.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final GameClient gameClient;

    public Cart getCart(UUID cartId) {
        return cartRepository
                .findById(cartId)
                .orElseGet(() -> cartRepository.save(new Cart(cartId)));
    }

    public Optional<Cart> setItemCount(UUID cartId, UUID productId, int count)
    {
        GameDto game = gameClient.get(productId);
        if(game == null) return Optional.empty();

        Cart cart = getCart(cartId);
        if(count < 0) {
            cart.getItems().remove(productId);
        } else {
            cart.getItems().put(productId, new CartItem(count, game.getCost()));
        }

        return Optional.of(cartRepository.save(cart));
    }

    public void deleteCart(UUID cartId) {
        cartRepository.deleteById(cartId);
    }
}

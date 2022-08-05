package com.cdprojekt.store.service.order.service;

import com.cdprojekt.store.service.order.repository.OrderRepository;
import com.cdprojekt.store.shared.order.Cart;
import com.cdprojekt.store.shared.order.Order;
import com.cdprojekt.store.shared.order.OrderItem;
import com.cdprojekt.store.shared.order.OrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;

    public Order createOrder(Cart cart) {
        Order order = orderRepository.save(this.initOrder(cart));
        cartService.deleteCart(cart.getId());
        return order;
    }

    private Order initOrder(Cart cart) {
        Order order = new Order();

        var orderItems = cart
                .getItems()
                .entrySet()
                .stream()
                .map((entry) -> {
                    var orderItem = new OrderItem();
                    orderItem.setProductId(entry.getKey());
                    orderItem.setCost(entry.getValue().cost());
                    orderItem.setCount(entry.getValue().count());
                    orderItem.setOrder(order);
                    return orderItem;
                }).collect(Collectors.toList());

        order.setItems(orderItems);
        order.setOrderedAt(LocalDate.now());
        order.setState(OrderState.AWAITING_PAYMENT);
        return order;
    }
}

package com.cdprojekt.store.shared.order;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RedisHash
@Data
public class Cart {
    @Id
    private UUID id;
    private Map<UUID, CartItem> items;

    public Cart(UUID id) {
        this.id = id;
        this.items = new ConcurrentHashMap<>();
    }
}

package com.cdprojekt.store.service.order.repository;

import com.cdprojekt.store.shared.order.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends CrudRepository<Cart, UUID> {}

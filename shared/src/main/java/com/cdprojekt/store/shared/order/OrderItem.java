package com.cdprojekt.store.shared.order;

import com.cdprojekt.store.shared.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="order_items")
@Getter
@Setter
public class OrderItem extends BaseEntity {
    @Column(nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false)
    private int count;

    @ManyToOne(optional = false)
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}

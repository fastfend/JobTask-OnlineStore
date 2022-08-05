package com.cdprojekt.store.shared.order;

import java.math.BigDecimal;

public record CartItem(int count, BigDecimal cost) {
}

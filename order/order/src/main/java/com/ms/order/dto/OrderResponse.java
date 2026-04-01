package com.ms.order.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Long id,
        Long userId,
        BigDecimal totalAmount,
        String status,
        List<OrderItemResponse> items
) {
}
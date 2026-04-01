package com.ms.order.dto;

import java.math.BigDecimal;

public record PaymentResponse(
        Long id,
        Long orderId,
        BigDecimal amount,
        String status
) {
}
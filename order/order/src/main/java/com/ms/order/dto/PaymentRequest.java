package com.ms.order.dto;

import java.math.BigDecimal;

public record PaymentRequest(
        Long orderId,
        BigDecimal amount
) {
}
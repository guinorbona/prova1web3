package com.ms.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(

        @NotNull(message = "O productId é obrigatório")
        Long productId,

        @NotNull(message = "A quantity é obrigatória")
        @Min(value = 1, message = "A quantity deve ser no mínimo 1")
        Integer quantity
) {
}
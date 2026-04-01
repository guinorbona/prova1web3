package com.ms.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(

        @NotNull(message = "O userId é obrigatório")
        Long userId,

        @NotEmpty(message = "O pedido deve ter ao menos um item")
        List<@Valid OrderItemRequest> items
) {
}
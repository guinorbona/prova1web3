package com.ms.order.dto;

public record InventoryResponse(
        Long id,
        Long productId,
        Integer quantity
) {
}
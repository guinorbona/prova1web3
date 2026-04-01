package com.ms.inventory.dto;

public record InventoryResponse(
        Long id,
        Long productId,
        Integer quantity
) {
}
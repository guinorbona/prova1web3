package com.ms.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateInventoryRequest(

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 0, message = "A quantidade não pode ser negativa")
        Integer quantity
) {
}
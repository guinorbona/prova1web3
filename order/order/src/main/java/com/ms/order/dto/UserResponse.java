package com.ms.order.dto;

public record UserResponse(
        Long id,
        String name,
        String email
) {
}
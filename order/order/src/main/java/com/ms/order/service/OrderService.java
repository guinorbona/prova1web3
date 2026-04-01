package com.ms.order.service;

import com.ms.order.dto.CreateOrderRequest;
import com.ms.order.dto.OrderResponse;

public interface OrderService {

    OrderResponse create(CreateOrderRequest request);

    OrderResponse findById(Long id);
}
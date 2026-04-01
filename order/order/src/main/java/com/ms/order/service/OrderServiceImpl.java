package com.ms.order.service;

import com.ms.order.client.InventoryClient;
import com.ms.order.client.PaymentClient;
import com.ms.order.client.ProductClient;
import com.ms.order.client.UserClient;
import com.ms.order.dto.*;
import com.ms.order.entity.Order;
import com.ms.order.entity.OrderItem;
import com.ms.order.entity.OrderStatus;
import com.ms.order.exception.BusinessException;
import com.ms.order.exception.ResourceNotFoundException;
import com.ms.order.repository.OrderItemRepository;
import com.ms.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserClient userClient;
    private final ProductClient productClient;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            UserClient userClient,
                            ProductClient productClient,
                            InventoryClient inventoryClient,
                            PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userClient = userClient;
        this.productClient = productClient;
        this.inventoryClient = inventoryClient;
        this.paymentClient = paymentClient;
    }

    @Override
    public OrderResponse create(CreateOrderRequest request) {
        userClient.findById(request.userId());

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemRequest : request.items()) {
            ProductResponse product = productClient.findById(itemRequest.productId());
            InventoryResponse inventory = inventoryClient.findByProductId(itemRequest.productId());

            if (inventory.quantity() < itemRequest.quantity()) {
                throw new BusinessException("Estoque insuficiente para o productId " + itemRequest.productId());
            }

            BigDecimal itemTotal = product.price().multiply(BigDecimal.valueOf(itemRequest.quantity()));
            totalAmount = totalAmount.add(itemTotal);

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(itemRequest.productId());
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setUnitPrice(product.price());

            orderItems.add(orderItem);
        }

        Order order = new Order();
        order.setUserId(request.userId());
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);

        for (OrderItem item : orderItems) {
            item.setOrderId(savedOrder.getId());
            orderItemRepository.save(item);
        }

        PaymentRequest paymentRequest = new PaymentRequest(savedOrder.getId(), totalAmount);
        PaymentResponse paymentResponse = paymentClient.processPayment(paymentRequest);

        if ("APPROVED".equalsIgnoreCase(paymentResponse.status())) {
            savedOrder.setStatus(OrderStatus.APPROVED);
            orderRepository.save(savedOrder);

            for (OrderItem item : orderItems) {
                InventoryResponse currentInventory = inventoryClient.findByProductId(item.getProductId());

                int newQuantity = currentInventory.quantity() - item.getQuantity();

                inventoryClient.updateByProductId(
                        item.getProductId(),
                        new UpdateInventoryRequest(newQuantity)
                );
            }
        } else {
            savedOrder.setStatus(OrderStatus.REJECTED);
            orderRepository.save(savedOrder);
        }

        return buildOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com id " + id + " não encontrado"));

        return buildOrderResponse(order);
    }

    private OrderResponse buildOrderResponse(Order order) {
        List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());

        List<OrderItemResponse> itemResponses = items.stream()
                .map(item -> new OrderItemResponse(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getUnitPrice()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getTotalAmount(),
                order.getStatus().name(),
                itemResponses
        );
    }
}
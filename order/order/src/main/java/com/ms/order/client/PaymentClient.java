package com.ms.order.client;

import com.ms.order.dto.PaymentRequest;
import com.ms.order.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8084")
public interface PaymentClient {

    @PostMapping("/payments")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
}
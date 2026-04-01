package com.ms.order.client;

import com.ms.order.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse findById(@PathVariable("id") Long id);
}
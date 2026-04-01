package com.ms.order.client;

import com.ms.order.dto.InventoryResponse;
import com.ms.order.dto.UpdateInventoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventory-service", url = "http://localhost:8082")
public interface InventoryClient {

    @GetMapping("/inventory/{productId}")
    InventoryResponse findByProductId(@PathVariable("productId") Long productId);

    @PutMapping("/inventory/{productId}")
    InventoryResponse updateByProductId(@PathVariable("productId") Long productId,
                                        @RequestBody UpdateInventoryRequest request);
}
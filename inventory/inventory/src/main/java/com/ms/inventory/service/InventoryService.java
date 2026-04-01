package com.ms.inventory.service;

import com.ms.inventory.dto.InventoryRequest;
import com.ms.inventory.dto.InventoryResponse;
import com.ms.inventory.dto.UpdateInventoryRequest;

public interface InventoryService {

    InventoryResponse create(InventoryRequest request);

    InventoryResponse findByProductId(Long productId);

    InventoryResponse updateByProductId(Long productId, UpdateInventoryRequest request);
}
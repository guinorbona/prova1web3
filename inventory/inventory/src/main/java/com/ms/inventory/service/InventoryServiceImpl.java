package com.ms.inventory.service;

import com.ms.inventory.dto.InventoryRequest;
import com.ms.inventory.dto.InventoryResponse;
import com.ms.inventory.dto.UpdateInventoryRequest;
import com.ms.inventory.entity.Inventory;
import com.ms.inventory.exception.BusinessException;
import com.ms.inventory.exception.ResourceNotFoundException;
import com.ms.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryResponse create(InventoryRequest request) {
        if (inventoryRepository.existsByProductId(request.productId())) {
            throw new BusinessException("Já existe estoque cadastrado para o productId " + request.productId());
        }

        Inventory inventory = new Inventory();
        inventory.setProductId(request.productId());
        inventory.setQuantity(request.quantity());

        Inventory savedInventory = inventoryRepository.save(inventory);
        return toResponse(savedInventory);
    }

    @Override
    public InventoryResponse findByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado para o productId " + productId));

        return toResponse(inventory);
    }

    @Override
    public InventoryResponse updateByProductId(Long productId, UpdateInventoryRequest request) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado para o productId " + productId));

        inventory.setQuantity(request.quantity());

        Inventory updatedInventory = inventoryRepository.save(inventory);
        return toResponse(updatedInventory);
    }

    private InventoryResponse toResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getQuantity()
        );
    }
}
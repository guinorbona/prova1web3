package com.ms.inventory.controller;

import com.ms.inventory.dto.InventoryRequest;
import com.ms.inventory.dto.InventoryResponse;
import com.ms.inventory.dto.UpdateInventoryRequest;
import com.ms.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> create(@RequestBody @Valid InventoryRequest request) {
        InventoryResponse response = inventoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.findByProductId(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<InventoryResponse> updateByProductId(@PathVariable Long productId,
                                                               @RequestBody @Valid UpdateInventoryRequest request) {
        return ResponseEntity.ok(inventoryService.updateByProductId(productId, request));
    }
}
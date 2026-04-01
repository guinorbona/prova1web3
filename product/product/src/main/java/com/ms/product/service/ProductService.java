package com.ms.product.service;

import com.ms.product.dto.ProductRequest;
import com.ms.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    List<ProductResponse> findAll();

    ProductResponse findById(Long id);
}

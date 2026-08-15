package com.billflow.service;

import com.billflow.dto.request.ProductRequest;
import com.billflow.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();
}

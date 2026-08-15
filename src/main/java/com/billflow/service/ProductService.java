package com.billflow.service;

import com.billflow.dto.request.ProductRequest;
import com.billflow.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
}

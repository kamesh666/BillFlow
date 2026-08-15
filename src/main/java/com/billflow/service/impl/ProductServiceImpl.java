package com.billflow.service.impl;

import com.billflow.dto.request.ProductRequest;
import com.billflow.dto.response.ProductResponse;
import com.billflow.entity.Product;
import com.billflow.entity.Tenant;
import com.billflow.repository.ProductRepository;
import com.billflow.repository.TenantRepository;
import com.billflow.security.TenantContext;
import com.billflow.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final TenantRepository tenantRepository;
    private final ProductRepository productRepository;
    private final TenantContext tenantContext;

    public ProductServiceImpl(TenantRepository tenantRepository, ProductRepository productRepository, TenantContext tenantContext) {
        this.tenantRepository = tenantRepository;
        this.productRepository = productRepository;
        this.tenantContext = tenantContext;
    }

    @Transactional
    @Override
    public ProductResponse createProduct(ProductRequest request){
        Long tenantId = tenantContext.getCurrentTenantId();
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow(()-> new RuntimeException("Tenannt not found"));

        Product product = new Product();
        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setDescription(request.getDescription());
        product.setSellingPrice(request.getSellingPrice());
        product.setPurchasePrice(request.getPurchasePrice());
        product.setTaxRate(request.getTaxRate());
        product.setTenant(tenant);
        product.setDeleted(false);
        productRepository.save(product);

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSku(),
                product.getDescription(),
                product.getSellingPrice(),
                product.getPurchasePrice(),
                product.getTaxRate()
        );
    }
}

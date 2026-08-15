package com.billflow.repository;

import com.billflow.entity.Customer;
import com.billflow.entity.Product;
import com.billflow.entity.Tenant;
import com.billflow.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByTenantAndStatus(Tenant tenant, ProductStatus status);

    Optional<Product> findByIdAndTenantAndStatus(Long id, Tenant tenant, ProductStatus status);
}

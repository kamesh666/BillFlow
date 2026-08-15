package com.billflow.entity;

import com.billflow.enums.ProductStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends BaseEntity {
    private String name;
    private String sku;
    private String description;
    private Double sellingPrice;
    private Double purchasePrice;
    private Double taxRate;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ACTIVE;

    @Column(nullable = false)
    private Boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id" , nullable = false)
    private Tenant tenant;
}

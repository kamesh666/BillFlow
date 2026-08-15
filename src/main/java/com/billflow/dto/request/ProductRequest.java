package com.billflow.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String sku;
    private String description;
    private Double sellingPrice;
    private Double purchasePrice;
    private Double taxRate;
}
package com.billflow.dto.response;

public record ProductResponse(
        Long id,
        String name,
        String sku,
        String description,
        Double sellingPrice,
        Double purchasePrice,
        Double taxRate
        ) {
}

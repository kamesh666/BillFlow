package com.billflow.dto.response;

public record CustomerResponse(
    Long id,
    String name,
    String phone,
    String email,
    String address,
    String gstNumber
) {
}

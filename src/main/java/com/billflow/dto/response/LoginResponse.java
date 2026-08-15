package com.billflow.dto.response;

public record LoginResponse(
    String token,
    String message,
    Long tenantId,
    String companyName,
    String role
) {
}

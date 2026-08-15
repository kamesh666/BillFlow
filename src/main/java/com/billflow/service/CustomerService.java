package com.billflow.service;

import com.billflow.dto.request.CustomerRequest;
import com.billflow.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
}

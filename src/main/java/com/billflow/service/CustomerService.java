package com.billflow.service;

import java.util.List;

import com.billflow.dto.request.CustomerRequest;
import com.billflow.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(Long id, CustomerRequest request);

    void deleteCustomer(Long id);
}

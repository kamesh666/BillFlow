package com.billflow.service.impl;

import org.springframework.stereotype.Service;

import com.billflow.dto.request.CustomerRequest;
import com.billflow.dto.response.CustomerResponse;
import com.billflow.entity.Customer;
import com.billflow.entity.Tenant;
import com.billflow.repository.CustomerRepository;
import com.billflow.repository.TenantRepository;
import com.billflow.service.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final TenantRepository tenantRepository;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(
        TenantRepository tenantRepository,
        CustomerRepository customerRepository
    ) {
        this.tenantRepository = tenantRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public CustomerResponse createCustomer(CustomerRequest request){
        Long tenantId = 8L;
        Tenant tenant = tenantRepository.findById(tenantId)
            .orElseThrow(()-> new RuntimeException("Tenant not found"));
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setGstNumber(request.getGstNumber());
        customer.setTenant(tenant);

        customerRepository.save(customer);

        return new CustomerResponse(
            customer.getId(),
            customer.getName(),
            customer.getEmail(),
            customer.getPhone(),
            customer.getAddress(),
            customer.getGstNumber()
        );
    }
}

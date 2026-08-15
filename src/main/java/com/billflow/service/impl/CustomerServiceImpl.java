package com.billflow.service.impl;

import java.util.List;

import com.billflow.security.TenantContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.billflow.dto.request.CustomerRequest;
import com.billflow.dto.response.CustomerResponse;
import com.billflow.entity.Customer;
import com.billflow.entity.Tenant;
import com.billflow.enums.CustomerStatus;
import com.billflow.repository.CustomerRepository;
import com.billflow.repository.TenantRepository;
import com.billflow.security.CustomUserDetails;
import com.billflow.service.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final TenantRepository tenantRepository;
    private final CustomerRepository customerRepository;
    private final TenantContext tenantContext;

    public CustomerServiceImpl(
        TenantRepository tenantRepository,
        CustomerRepository customerRepository,
        TenantContext tenantContext
    ) {
        this.tenantRepository = tenantRepository;
        this.customerRepository = customerRepository;
        this.tenantContext = tenantContext;
    }

    @Transactional
    @Override
    public CustomerResponse createCustomer(CustomerRequest request){
        Long tenantId = tenantContext.getCurrentTenantId();
        Tenant tenant = tenantRepository.findById(tenantId)
            .orElseThrow(()-> new RuntimeException("Tenant not found"));
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setGstNumber(request.getGstNumber());
        customer.setTenant(tenant);
        customer.setDeleted(true);
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

    @Override
    public List<CustomerResponse> getAllCustomers(){
        Long tenantId = tenantContext.getCurrentTenantId();

        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow(()-> new RuntimeException("Tenant not found"));

        return customerRepository.findByTenantAndStatus(tenant, CustomerStatus.ACTIVE)
        .stream()
        .map(customer -> new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getGstNumber()
        ))
        .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id){
        Long tenantId = tenantContext.getCurrentTenantId();
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow(()-> new RuntimeException("Tenant not found"));

       Customer customer = customerRepository
        .findByIdAndTenantAndStatus(id, tenant, CustomerStatus.ACTIVE)
        .orElseThrow(() -> new RuntimeException("Customer not found"));

        return new CustomerResponse(
            customer.getId(),
            customer.getName(),
            customer.getPhone(),
            customer.getEmail(),
            customer.getAddress(),
            customer.getGstNumber()
        );
    }

    @Transactional
    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest request){
        Long tenantId = tenantContext.getCurrentTenantId();

        Tenant tenant = tenantRepository.findById(tenantId)
            .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Customer customer = customerRepository
        .findByIdAndTenantAndStatus(id, tenant, CustomerStatus.ACTIVE)
        .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setGstNumber(request.getGstNumber());

        customerRepository.save(customer);

        return new CustomerResponse(
            customer.getId(),
            customer.getName(),
            customer.getPhone(),
            customer.getEmail(),
            customer.getAddress(),
            customer.getGstNumber()
        );
    }

    @Transactional
@Override
public void deleteCustomer(Long id) {

    Long tenantId = tenantContext.getCurrentTenantId();

    Tenant tenant = tenantRepository.findById(tenantId)
            .orElseThrow(() -> new RuntimeException("Tenant not found"));

    Customer customer = customerRepository
            .findByIdAndTenantAndStatus(id, tenant, CustomerStatus.ACTIVE)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    customer.setStatus(CustomerStatus.DELETED);

    customerRepository.save(customer);
}
}

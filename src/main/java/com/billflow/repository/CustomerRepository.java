package com.billflow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billflow.dto.request.CustomerRequest;
import com.billflow.dto.response.CustomerResponse;
import com.billflow.entity.Customer;
import com.billflow.entity.Tenant;
import com.billflow.enums.CustomerStatus;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findByTenantAndStatus(Tenant tenant, CustomerStatus status);

    Optional<Customer> findByIdAndTenantAndStatus(Long id, Tenant tenant, CustomerStatus status);
    
}

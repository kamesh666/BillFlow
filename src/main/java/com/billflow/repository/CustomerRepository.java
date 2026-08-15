package com.billflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billflow.entity.Customer;
import com.billflow.entity.Tenant;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findByTenant(Tenant tenant);
    
}

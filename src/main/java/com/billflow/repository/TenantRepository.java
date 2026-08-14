package com.billflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billflow.entity.Tenant;


public interface TenantRepository extends JpaRepository<Tenant, Long> {

    boolean existsByCompanyEmail(String companyEmail);
}

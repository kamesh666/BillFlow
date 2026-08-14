package com.billflow.entity;

import com.billflow.enums.TenantStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenants")
@Getter
@Setter
public class Tenant extends BaseEntity {
    
    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String gstNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private TenantStatus status;
}

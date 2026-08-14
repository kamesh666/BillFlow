package com.billflow.entity;

import org.hibernate.annotations.ManyToAny;

import com.billflow.enums.UserRole;
import com.billflow.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tools.jackson.databind.annotation.EnumNaming;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;
}

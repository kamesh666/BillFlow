package com.billflow.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.billflow.dto.request.LoginRequest;
import com.billflow.dto.request.RegisterRequest;
import com.billflow.dto.response.LoginResponse;
import com.billflow.dto.response.RegisterResponse;
import com.billflow.entity.Tenant;
import com.billflow.entity.User;
import com.billflow.enums.TenantStatus;
import com.billflow.enums.UserRole;
import com.billflow.enums.UserStatus;
import com.billflow.repository.TenantRepository;
import com.billflow.repository.UserRepository;
import com.billflow.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
        TenantRepository tenantRepository,
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ){
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public RegisterResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getAdminEmail())){
            throw new RuntimeException("Admin Email already exists");
            // return new RegisterResponse("Admin Email already exists");
        }

        if(tenantRepository.existsByCompanyEmail(request.getCompanyEmail())){
            throw new RuntimeException("Company email is already exists");
        }
        
        Tenant tenant = new Tenant();
        tenant.setCompanyName(request.getCompanyName());
        tenant.setCompanyEmail(request.getCompanyEmail());
        tenant.setCompanyPhone(request.getCompanyPhone());
        tenant.setStatus(TenantStatus.ACTIVE);

        tenantRepository.save(tenant);

        User user = new User();
        user.setFullName(request.getAdminName());
        user.setEmail(request.getAdminEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getCompanyPhone());   // MVP
        user.setRole(UserRole.ADMIN);
        user.setStatus(UserStatus.ACTIVE);
        user.setTenant(tenant);

        userRepository.save(user);
        return new RegisterResponse("Registration completed Successfully");
    }

    @Override
    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("Invalid credentials"));
        String hash = user.getPassword();
        boolean isValid = passwordEncoder.matches(request.getPassword(), hash);
        if(!isValid){
            throw new RuntimeException("Invalid credentials");
        }
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new RuntimeException("Account is inactive");
        }
        return new LoginResponse("Login Successful");
    }
}

package com.billflow.service;

import com.billflow.dto.request.LoginRequest;
import com.billflow.dto.request.RegisterRequest;
import com.billflow.dto.response.LoginResponse;
import com.billflow.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}
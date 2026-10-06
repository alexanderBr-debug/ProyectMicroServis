package com.yers7.auth_service.application.ports.in;

import com.yers7.auth_service.domain.model.TokenPair;

public interface LoginUseCase {

    TokenPair login(String email,String password);
} 
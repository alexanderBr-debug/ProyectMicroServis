package com.yers7.auth_service.application.ports.in;

import com.yers7.auth_service.domain.model.TokenPair;
import com.yers7.auth_service.domain.model.User;

public interface RegisterUseCase {
    TokenPair register(String name,String email,String password);
}

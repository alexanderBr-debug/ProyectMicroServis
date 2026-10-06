package com.yers7.auth_service.application.ports.in;

import com.yers7.auth_service.domain.model.TokenPair;

public interface ValidateTokenUseCase {

    boolean validate(String token);
    TokenPair rotateRefreshToken(String rawTokenClient);
    
}

package com.yers7.auth_service.infrastructure.adapters.in.web.dto;

public record AuthResponse(
    String accessToken,
    String refreshToken
) {
    
}

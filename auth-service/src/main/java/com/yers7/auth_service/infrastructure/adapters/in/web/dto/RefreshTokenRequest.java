package com.yers7.auth_service.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(

    @NotBlank (message = "refreshToken is mandatory")
    String refreshToken
) {
    
}

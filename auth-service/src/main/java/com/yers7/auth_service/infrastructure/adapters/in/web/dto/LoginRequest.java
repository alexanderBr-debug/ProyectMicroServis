package com.yers7.auth_service.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

    @NotBlank (message = "email is mandatory")
    @Email (message = "invalid email format")
    String email,

    @NotBlank (message = "password is mandatory")
    String password

) {
    
}

package com.yers7.auth_service.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

    @NotBlank (message = "name is mandatory")
    String name,

    @NotBlank (message = "email is mandatory")
    @Email (message = "invalid email format ")
    String email,

    @NotBlank (message = "password is mandatory")
    @Size  (min = 6, message = "password must be at least 6 charecters")
    String password
) {
    
}

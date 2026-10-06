package com.yers7.auth_service.infrastructure.adapters.out.messaging;

public record UserRegisterEvent(
    String userId,
    String name,
    String email
) {
    
}

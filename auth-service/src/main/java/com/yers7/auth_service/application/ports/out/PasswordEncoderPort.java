package com.yers7.auth_service.application.ports.out;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
    boolean matches(String rawPassword,String encodePassword);
}

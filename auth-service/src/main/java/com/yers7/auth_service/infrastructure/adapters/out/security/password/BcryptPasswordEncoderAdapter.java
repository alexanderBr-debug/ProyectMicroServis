package com.yers7.auth_service.infrastructure.adapters.out.security.password;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.yers7.auth_service.application.ports.out.PasswordEncoderPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class BcryptPasswordEncoderAdapter implements PasswordEncoderPort {
    
    private final PasswordEncoder passwordEncoder;


    
    @Override 
    public String encode(String rawPassword){
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword,String encodePassword){
        return passwordEncoder.matches(rawPassword, encodePassword);
    }
}

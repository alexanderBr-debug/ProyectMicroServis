package com.yers7.auth_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yers7.auth_service.application.ports.out.PasswordEncoderPort;
import com.yers7.auth_service.application.ports.out.RefreshTokenRepositoryPort;
import com.yers7.auth_service.application.ports.out.TokenProviderPort;
import com.yers7.auth_service.application.ports.out.UserEventPublisherPort;
import com.yers7.auth_service.application.ports.out.UserRepositoryPort;
import com.yers7.auth_service.application.service.AuthService;
import com.yers7.auth_service.application.service.TokenService;

@Configuration 
public class AplicationBeanConfig {
    
    @Bean 
    public TokenService tokenService(

        UserEventPublisherPort userEventPublisherPort,
        RefreshTokenRepositoryPort refreshTokenRepositoryPort,
        TokenProviderPort tokenProviderPort
    ){
        return new TokenService(userEventPublisherPort, refreshTokenRepositoryPort, tokenProviderPort);
    }

    @Bean 
    public AuthService authService(

        UserRepositoryPort userRepositoryPort,
        PasswordEncoderPort passwordEncoderPort,
        TokenProviderPort tokenProviderPort,
        UserEventPublisherPort userEventPublisherPort,
        RefreshTokenRepositoryPort refreshTokenRepositoryPort){
            
        return new AuthService(userRepositoryPort, passwordEncoderPort, tokenProviderPort, userEventPublisherPort, refreshTokenRepositoryPort);
     }
}

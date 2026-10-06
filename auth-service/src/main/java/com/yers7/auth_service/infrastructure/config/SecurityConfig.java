package com.yers7.auth_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yers7.auth_service.infrastructure.adapters.in.web.filter.JwtAuthenticationFilter;
import com.yers7.auth_service.infrastructure.adapters.in.web.filter.RateLimitFilter;

import lombok.RequiredArgsConstructor;

@Configuration 
@EnableWebSecurity 
@RequiredArgsConstructor 
public class SecurityConfig {

    private final RateLimitFilter rateLimitFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        
        return http

        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()  
        )
        
        .addFilterBefore(rateLimitFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
        .build();
    }
}

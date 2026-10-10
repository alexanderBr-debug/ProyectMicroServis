package com.yers7.auth_service.infrastructure.adapters.in.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yers7.auth_service.application.ports.in.LoginUseCase;
import com.yers7.auth_service.application.ports.in.RegisterUseCase;
import com.yers7.auth_service.application.ports.in.ValidateTokenUseCase;
import com.yers7.auth_service.domain.model.TokenPair;
import com.yers7.auth_service.infrastructure.adapters.in.web.dto.AuthResponse;
import com.yers7.auth_service.infrastructure.adapters.in.web.dto.LoginRequest;
import com.yers7.auth_service.infrastructure.adapters.in.web.dto.RefreshTokenRequest;
import com.yers7.auth_service.infrastructure.adapters.in.web.dto.RegisterRequest;
import com.yers7.auth_service.infrastructure.adapters.in.web.mapper.AuthWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/auth")
public class AuthController {
    
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;
    private final AuthWebMapper authWebMapper;

    @PostMapping ("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        TokenPair authResponse = loginUseCase.login(loginRequest.email(),loginRequest.password());
        return ResponseEntity.ok(authWebMapper.toAuthResponse(authResponse));
    }

    @PostMapping ("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
       TokenPair auTokenPair = registerUseCase.register(registerRequest.name(),registerRequest.email(),registerRequest.password());
        return ResponseEntity.ok(authWebMapper.toAuthResponse(auTokenPair));
    }

    @PostMapping ("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        TokenPair tokenPair = validateTokenUseCase.rotateRefreshToken(refreshTokenRequest.refreshToken());
        return ResponseEntity.ok(authWebMapper.toAuthResponse(tokenPair));
    }
    
}

package com.yers7.auth_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class TokenPair {
    private String accessToken;
    private String refreshToken;
}

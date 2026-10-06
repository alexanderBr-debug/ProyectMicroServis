package com.yers7.auth_service.infrastructure.adapters.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yers7.auth_service.domain.model.TokenPair;
import com.yers7.auth_service.infrastructure.adapters.in.web.dto.AuthResponse;

@Mapper (componentModel = "spring")
public interface AuthWebMapper {
    
    @Mapping (source = "accessToken",target = "accessToken")
    @Mapping (source = "refreshToken",target = "refreshToken")
    AuthResponse toAuthResponse(TokenPair tokenPair);
}

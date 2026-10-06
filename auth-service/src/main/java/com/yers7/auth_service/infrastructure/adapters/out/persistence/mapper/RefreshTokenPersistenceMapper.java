package com.yers7.auth_service.infrastructure.adapters.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.yers7.auth_service.domain.model.RefreshToken;

import com.yers7.auth_service.infrastructure.adapters.out.persistence.entity.RefreshTokenEntity;



@Mapper (componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RefreshTokenPersistenceMapper {
    
   
   RefreshTokenEntity toRefreshTokenEntity(RefreshToken refreshToken);
   RefreshToken toDomain(RefreshTokenEntity refreshTokenEntity);
}

package com.yers7.auth_service.infrastructure.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yers7.auth_service.application.ports.out.RefreshTokenRepositoryPort;
import com.yers7.auth_service.domain.model.RefreshToken;
import com.yers7.auth_service.domain.model.User;
import com.yers7.auth_service.infrastructure.adapters.out.persistence.entity.RefreshTokenEntity;
import com.yers7.auth_service.infrastructure.adapters.out.persistence.mapper.RefreshTokenPersistenceMapper;
import com.yers7.auth_service.infrastructure.adapters.out.persistence.repository.SpringDataRefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class RefreshTokenPersistenceAdapter implements RefreshTokenRepositoryPort {
    
    private final SpringDataRefreshTokenRepository springDataRefreshTokenRepository;
    private final RefreshTokenPersistenceMapper refreshTokenPersistenceMapper;

    @Override 
    public RefreshToken save(RefreshToken refreshToken){

        RefreshTokenEntity entity = refreshTokenPersistenceMapper.toRefreshTokenEntity(refreshToken);
        RefreshTokenEntity saved = springDataRefreshTokenRepository.save(entity);

        return refreshTokenPersistenceMapper.toDomain(saved);
    }

    @Override 
    public Optional<RefreshToken> findByToken(String token){

        return springDataRefreshTokenRepository.findByToken(token)
        .map(refreshTokenPersistenceMapper::toDomain);
    }

    @Override 
    public List<RefreshToken> findAllByFamilyId(UUID familyId){
        return springDataRefreshTokenRepository.findAllByFamilyId(familyId).stream()
        .map(refreshTokenPersistenceMapper::toDomain)
        .toList();
    }

    @Override 
    @Transactional 
    public void revokeByToken(String token){
        springDataRefreshTokenRepository.revokeByToken(token);
    }

    @Override 
    public List<RefreshToken> saveAll(List<RefreshToken> refreshTokens){

       List<RefreshTokenEntity> entities = refreshTokens.stream()
       .map(refreshTokenPersistenceMapper::toRefreshTokenEntity)
       .toList();

       return springDataRefreshTokenRepository.saveAll(entities).stream()
       .map(refreshTokenPersistenceMapper::toDomain)
       .toList();
    }

    @Override 
    @Transactional 
    public void revokeAllByUser(User user){
        springDataRefreshTokenRepository.revokeAllByUserId(user.getId());
    }
}
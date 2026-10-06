package com.yers7.auth_service.application.ports.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.yers7.auth_service.domain.model.RefreshToken;
import com.yers7.auth_service.domain.model.User;


public interface RefreshTokenRepositoryPort {

    RefreshToken save(RefreshToken refreshToken);
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken>  findAllByFamilyId(UUID familyId);
    List<RefreshToken> saveAll(List<RefreshToken> refreshTokens);
    void revokeByToken(String token);
    void revokeAllByUser(User user);
}

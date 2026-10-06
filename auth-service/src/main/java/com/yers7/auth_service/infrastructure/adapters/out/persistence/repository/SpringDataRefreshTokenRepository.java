package com.yers7.auth_service.infrastructure.adapters.out.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yers7.auth_service.infrastructure.adapters.out.persistence.entity.RefreshTokenEntity;

public interface SpringDataRefreshTokenRepository extends JpaRepository<RefreshTokenEntity,UUID> {
    
    Optional<RefreshTokenEntity> findByToken(String token);

    List<RefreshTokenEntity> findAllByFamilyId(UUID familyId);

    @Modifying 
    @Query ("UPDATE RefreshTokenEntity r SET r.revoked = true, r.revokedAt = CURRENT_TIMESTAMP WHERE r.token = :token")
    void revokeByToken(@Param ("token") String token);

    @Modifying 
    @Query ("UPDATE RefreshTokenEntity r SET r.revoked = true, r.revokedAt = CURRENT_TIMESTAMP WHERE r.user.id = :userId")
    void revokeAllByUserId(@Param("userId") Long userId);
}

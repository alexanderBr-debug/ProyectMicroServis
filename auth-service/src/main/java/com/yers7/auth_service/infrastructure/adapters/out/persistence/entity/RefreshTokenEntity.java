package com.yers7.auth_service.infrastructure.adapters.out.persistence.entity;

import java.time.Instant;
import java.util.UUID;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@NoArgsConstructor 
@AllArgsConstructor 
@Setter 
@Getter 
@Builder 
@Table (name = "refresh_token",
    indexes = {
        @Index (name = "idx_refresh_token_user", columnList = "user_id")
    }
)
public class RefreshTokenEntity {
    
    @Id 
    @Column (name = "id",nullable = false,unique = true)
    private UUID id;

    @Column (nullable = false,unique = true,length = 512)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; 

    @Column (nullable = false)
    private Instant expiresAt;

    @Column (nullable = false)
    private Instant issueAt;

    @Column (nullable = false)
    @Builder.Default
    private boolean revoked = false;

    private Instant revokedAt;

    @Column (nullable = false)
    private UUID familyId;

    private UUID replaceByTokenId;



}

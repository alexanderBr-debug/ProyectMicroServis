package com.yers7.auth_service.domain.model;

import java.time.Instant;
import java.util.UUID;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter  
@Builder 
public class RefreshToken {

    private UUID id;
    private String token;
    private User user;
    private Instant expiresAt;
    private Instant issueAt;
    @Builder.Default
    private boolean revoked = false;
    private Instant revokedAt;
    private UUID familyId;
    private UUID replaceByTokenId;



    

    
}

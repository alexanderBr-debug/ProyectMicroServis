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

   public RefreshToken(){
    }

   public RefreshToken(UUID id,String token,User user,Instant expiresAt,Instant issueAt,boolean revoked,Instant revokedAt,UUID familyId,UUID replaceByTokenId){
        this.id = id;
        this.token = token;
        this.user = user;
        this.expiresAt = expiresAt;
        this.issueAt = issueAt;
        this.revoked = revoked;
        this.revokedAt = revokedAt;
        this.familyId = familyId;
        this.replaceByTokenId = replaceByTokenId;
    }
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

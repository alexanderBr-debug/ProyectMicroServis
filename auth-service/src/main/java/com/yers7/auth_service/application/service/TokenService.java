package com.yers7.auth_service.application.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;




import com.yers7.auth_service.application.ports.in.ValidateTokenUseCase;
import com.yers7.auth_service.application.ports.out.RefreshTokenRepositoryPort;
import com.yers7.auth_service.application.ports.out.TokenProviderPort;
import com.yers7.auth_service.application.ports.out.UserEventPublisherPort;
import com.yers7.auth_service.domain.exception.TokenExpiredException;
import com.yers7.auth_service.domain.exception.TokenNotFoundException;
import com.yers7.auth_service.domain.exception.TokenReusedException;
import com.yers7.auth_service.domain.exception.TokenRevokedException;
import com.yers7.auth_service.domain.model.RefreshToken;
import com.yers7.auth_service.domain.model.TokenPair;
import com.yers7.auth_service.domain.model.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter 
@Getter 
@RequiredArgsConstructor 
public class TokenService implements ValidateTokenUseCase  {

    private final UserEventPublisherPort userEventPublisherPort;
    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final TokenProviderPort tokenProviderPort;
    private static final SecureRandom secureRandom = new SecureRandom();
    

   public String createRefreshToken(User user) {
        
        String rawToken =  generateSecureRandomToken();

        RefreshToken entity = RefreshToken.builder()
            
                .id(UUID.randomUUID())
                .token(hashToken(rawToken))
                .user(user)
                .issueAt(Instant.now())
                .expiresAt(Instant.now().plus(7, ChronoUnit.DAYS))
                .revoked(false)
                .familyId(UUID.randomUUID()) 
                .build();

        refreshTokenRepositoryPort.save(entity);
    
        return rawToken; 
    }

    public TokenPair rotateRefreshToken(String rawTokenFromClient) {
        String incomingHash = hashToken(rawTokenFromClient);

      
        RefreshToken storedToken = refreshTokenRepositoryPort.findByToken(incomingHash)
                .orElseThrow(() -> new TokenNotFoundException("Refresh token no reconocido"));

      
        if (storedToken.isRevoked() && storedToken.getReplaceByTokenId() != null) {

            revokeFamily(storedToken.getFamilyId());

            userEventPublisherPort.publishTokenRevokedEvent(storedToken.getUser());
            throw new TokenReusedException("Se detectó reutilización de un token ya rotado — posible robo");
        }

       
        if (storedToken.isRevoked()) {
            throw new TokenRevokedException("Este token ya fue revocado");
        }

       
        if (storedToken.getExpiresAt().isBefore(Instant.now())) {
            throw new TokenExpiredException("El refresh token expiró");
        }

      
        String newRawToken = generateSecureRandomToken();

        RefreshToken newToken = RefreshToken.builder()
                .id(UUID.randomUUID())
                .token(hashToken(newRawToken))
                .user(storedToken.getUser())
                .issueAt(Instant.now())
                .expiresAt(Instant.now().plus(7, ChronoUnit.DAYS))
                .revoked(false)
                .familyId(storedToken.getFamilyId()) // misma familia, no una nueva
                .build();
        refreshTokenRepositoryPort.save(newToken);

       
        storedToken.setRevoked(true);
        storedToken.setRevokedAt(Instant.now());
        storedToken.setReplaceByTokenId(newToken.getId());
        refreshTokenRepositoryPort.save(storedToken);

        String newAccessToken = tokenProviderPort.generatedAccessToken(storedToken.getUser().getEmail());

        return new TokenPair (newAccessToken, newRawToken);
    }

    public void revokeFamily(UUID familyId) {
        var tokens = refreshTokenRepositoryPort.findAllByFamilyId(familyId);
        for (RefreshToken t : tokens) {
            if (!t.isRevoked()) {
                t.setRevoked(true);
                t.setRevokedAt(Instant.now());
            }
        }
     refreshTokenRepositoryPort.saveAll(tokens);
    }

    private String generateSecureRandomToken() {
        byte[] randomBytes = new byte[64]; 
        secureRandom.nextBytes(randomBytes); 
        
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    private String hashToken(String rawToken) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(rawToken.getBytes()); 
            return Base64.getEncoder().encodeToString(hashBytes); 
            
        } catch (NoSuchAlgorithmException e) {
            
            throw new RuntimeException("Algoritmo de hash no disponible", e);
        }
    }

    @Override 
    public boolean validate(String token){
        try{
        String incomingHash = hashToken(token);

       Optional<RefreshToken> valid = refreshTokenRepositoryPort.findByToken(incomingHash);

        if(valid.isEmpty()){
            return false;
        }

        RefreshToken validate = valid.get();

        if (validate.isRevoked() || validate.getExpiresAt().isBefore(Instant.now())) {
            return false;
        }

        return true;   

        } catch(Exception e){
        return false;
     }
        
    
    }
}
   
    


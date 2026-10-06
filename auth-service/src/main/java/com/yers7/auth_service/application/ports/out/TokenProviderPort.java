package com.yers7.auth_service.application.ports.out;



public interface TokenProviderPort {
    String generatedAccessToken(String email);
    String generatedRefreshToken(String email);
    String extractUsername(String token);
    boolean isValidToken(String token,String emial);
}

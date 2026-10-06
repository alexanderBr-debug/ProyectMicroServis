package com.yers7.auth_service.domain.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message){
        super(message);
    }
    
}

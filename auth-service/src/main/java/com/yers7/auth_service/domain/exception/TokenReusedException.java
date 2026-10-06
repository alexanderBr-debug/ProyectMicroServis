package com.yers7.auth_service.domain.exception;

public class TokenReusedException extends RuntimeException {
    public TokenReusedException(String message){
        super(message);
    }
}

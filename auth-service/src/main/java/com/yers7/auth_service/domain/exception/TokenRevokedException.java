package com.yers7.auth_service.domain.exception;

public class TokenRevokedException extends RuntimeException {
    public TokenRevokedException(String message){
        super(message);
    }
}

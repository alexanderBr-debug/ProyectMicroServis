package com.yers7.auth_service.domain.exception;

public class RateLimitExceededException extends RuntimeException{
    public RateLimitExceededException(String message){
        super(message);
    }
}

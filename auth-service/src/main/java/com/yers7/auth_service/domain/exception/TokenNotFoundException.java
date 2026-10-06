package com.yers7.auth_service.domain.exception;

public class TokenNotFoundException extends RuntimeException  {
    
    public TokenNotFoundException(String msj){
        super(msj);
    }
}
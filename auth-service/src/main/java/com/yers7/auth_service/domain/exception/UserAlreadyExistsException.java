package com.yers7.auth_service.domain.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String message){
        super(message);
    }
    
}

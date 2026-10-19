package com.yers7.auth_service.domain.exception;

public class UserNotExistingException extends  RuntimeException{
    public UserNotExistingException(String msj){
        super(msj);
    }
}

package com.yers7.auth_service.infrastructure.adapters.in.web.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yers7.auth_service.domain.exception.InvalidCredentialsException;
import com.yers7.auth_service.domain.exception.RateLimitExceededException;
import com.yers7.auth_service.domain.exception.TokenExpiredException;
import com.yers7.auth_service.domain.exception.TokenNotFoundException;
import com.yers7.auth_service.domain.exception.TokenReusedException;
import com.yers7.auth_service.domain.exception.TokenRevokedException;
import com.yers7.auth_service.domain.exception.UserAlreadyExistsException;


@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler (InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> manejarInvalidCredentials(InvalidCredentialsException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "INVALID_CREDENTIALS");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler (RateLimitExceededException.class)
    public ResponseEntity<ErrorResponse> manejarRateLimit(RateLimitExceededException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "RATE_LIMIT_EXCEEDED");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(error);
    }

    @ExceptionHandler (TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> manejarTokenExpired(TokenExpiredException ex){
        ErrorResponse error = new  ErrorResponse(ex.getMessage(), "TOKEN_EXPIDED");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler (TokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarTokenNotFound(TokenNotFoundException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "TOKEN_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler (TokenReusedException.class)
    public ResponseEntity<ErrorResponse> manejarTokenReused(TokenReusedException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "SECURITY_ALERT_TOKEN_REUSED");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler (TokenRevokedException.class)
    public ResponseEntity<ErrorResponse> manejarTokenRevoked(TokenRevokedException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "TOKEN_REVOKED");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler (UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> manejarUserAlreadyExist(UserAlreadyExistsException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "USER_ALREADY_EXIST");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}

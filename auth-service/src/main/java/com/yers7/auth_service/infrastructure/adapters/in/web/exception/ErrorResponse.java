package com.yers7.auth_service.infrastructure.adapters.in.web.exception;

import java.time.Instant;

public record ErrorResponse(
    String message,
    String errorCode,
    Instant timestamp
) {
    public ErrorResponse(String message,String errorCode){
        this(message, errorCode, Instant.now());
    }
}

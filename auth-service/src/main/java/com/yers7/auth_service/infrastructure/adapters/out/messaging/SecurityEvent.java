package com.yers7.auth_service.infrastructure.adapters.out.messaging;

import java.time.Instant;

public record SecurityEvent(
   String eventType,
   String email,
   Instant timeInstant
) {
    
}

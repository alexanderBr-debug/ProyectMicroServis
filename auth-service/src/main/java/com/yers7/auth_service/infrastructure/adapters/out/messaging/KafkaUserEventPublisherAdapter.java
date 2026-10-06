package com.yers7.auth_service.infrastructure.adapters.out.messaging;

import java.time.Instant;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.yers7.auth_service.application.ports.out.UserEventPublisherPort;
import com.yers7.auth_service.domain.model.User;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class KafkaUserEventPublisherAdapter implements UserEventPublisherPort {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    private static final String TOPIC_SECURITY_EVENTS = "user-securyte-events";
    private static final String TOPIC_USER = "user-register";


    @Override 
    public void publishTokenRevokedEvent(User user){
        kafkaTemplate.send(TOPIC_SECURITY_EVENTS, new SecurityEvent("TOKEN_REVOKED",user.getEmail(),Instant.now()));
    }

    @Override 
    public void publishTokensRevokedFamilyEvent(User user){
        kafkaTemplate.send(TOPIC_SECURITY_EVENTS, new SecurityEvent("ALL_TOKENS_REVOKED_SECURITY_ALERT",user.getEmail(),Instant.now()));
    }

    @Override 
    public void publishUserRegisterEvent(User user){
        kafkaTemplate.send(TOPIC_USER, new UserRegisterEvent(user.getId().toString(),user.getName(),user.getEmail()));
    }


}

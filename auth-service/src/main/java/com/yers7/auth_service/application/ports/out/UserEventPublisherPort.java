package com.yers7.auth_service.application.ports.out;

import com.yers7.auth_service.domain.model.User;

public interface UserEventPublisherPort {
    void publishTokenRevokedEvent(User user);
    void publishTokensRevokedFamilyEvent(User user);
    void publishUserRegisterEvent(User user);
}

package com.yers7.auth_service.application.ports.out;

import java.util.Optional;

import com.yers7.auth_service.domain.model.User;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByEmail( String email);
   
}

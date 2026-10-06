package com.yers7.auth_service.infrastructure.adapters.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.yers7.auth_service.application.ports.out.UserRepositoryPort;
import com.yers7.auth_service.domain.model.User;
import com.yers7.auth_service.infrastructure.adapters.out.persistence.entity.UserEntity;
import com.yers7.auth_service.infrastructure.adapters.out.persistence.mapper.UserPersistenceMapper;
import com.yers7.auth_service.infrastructure.adapters.out.persistence.repository.SpringDataUserRepository;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class UserPersistenceAdapter implements UserRepositoryPort {
    
    private final SpringDataUserRepository springDataUserRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override 
    public User save(User user){

        UserEntity entity = userPersistenceMapper.toUserEntity(user);
        UserEntity savedEntity = springDataUserRepository.save(entity);

        return userPersistenceMapper.toDomain(savedEntity);
    }

    @Override 
    public Optional<User> findByEmail(String email){
        return springDataUserRepository.findByEmail(email)
        .map(userPersistenceMapper::toDomain);
    }
}

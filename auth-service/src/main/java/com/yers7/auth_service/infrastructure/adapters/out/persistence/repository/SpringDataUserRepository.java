package com.yers7.auth_service.infrastructure.adapters.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yers7.auth_service.infrastructure.adapters.out.persistence.entity.UserEntity;

public interface SpringDataUserRepository extends JpaRepository<UserEntity,Long>{
    Optional<UserEntity> findByEmail(String email);
}

package com.yers7.auth_service.infrastructure.adapters.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.yers7.auth_service.domain.model.User;
import com.yers7.auth_service.infrastructure.adapters.out.persistence.entity.UserEntity;

@Mapper (componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPersistenceMapper {

      @Mapping (target = "id", source = "id")
     UserEntity toUserEntity(User user);

     @Mapping(target = "id", source = "id")
      User toDomain(UserEntity userEntity);
}

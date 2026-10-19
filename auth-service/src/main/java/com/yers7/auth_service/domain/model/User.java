package com.yers7.auth_service.domain.model;

import java.time.Instant;




import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Builder 
public class User {

   public User(){}

   public User( Long id,
     String email,
     String password,
     Role rol,
     Instant createdAt,
     String name){
        this.email = email;
        this.id = id;
        this.password = password;
        this.rol = rol;
        this.createdAt = createdAt;
        this.name = name;
     }

    private Long id;
    private String email;
    private String password;
    private Role rol;
    private Instant createdAt;
    private String name;

}

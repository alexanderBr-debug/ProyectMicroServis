package com.yers7.auth_service.domain.model;

import java.time.Instant;




import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Builder 
public class User {
    private Long id;
    private String email;
    private String password;
    private Role rol;
    private Instant createdAt;
    private String name;

}

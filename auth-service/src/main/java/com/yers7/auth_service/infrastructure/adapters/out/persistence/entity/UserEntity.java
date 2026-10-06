package com.yers7.auth_service.infrastructure.adapters.out.persistence.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yers7.auth_service.domain.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@NoArgsConstructor 
@AllArgsConstructor 
@Setter 
@Getter 
@Table (name = "users", indexes = {
    @Index(name = "idx_user_email",columnList = "email")})
public class UserEntity implements UserDetails {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    /*decimos que solo un usuario debe tener ese email con unique */
    @Column (nullable = false,unique = true,length = 100)
    private String email;

    @Column (nullable = false)
    private String password;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private Role rol;

    
     @Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    
}

@Override
    public String getUsername() {
    return this.email; 
}

@Override
    public boolean isAccountNonExpired() { return true; }

@Override
    public boolean isAccountNonLocked() { return true; }

@Override
    public boolean isCredentialsNonExpired() { return true; }

@Override
    public boolean isEnabled() { return true; }

@Override 
public String getPassword(){
    return this.password;
}



    
}



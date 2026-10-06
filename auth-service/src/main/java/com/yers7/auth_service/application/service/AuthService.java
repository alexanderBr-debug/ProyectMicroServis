package com.yers7.auth_service.application.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;




import com.yers7.auth_service.application.ports.in.LoginUseCase;
import com.yers7.auth_service.application.ports.in.RegisterUseCase;
import com.yers7.auth_service.application.ports.out.PasswordEncoderPort;
import com.yers7.auth_service.application.ports.out.RefreshTokenRepositoryPort;
import com.yers7.auth_service.application.ports.out.TokenProviderPort;
import com.yers7.auth_service.application.ports.out.UserEventPublisherPort;
import com.yers7.auth_service.application.ports.out.UserRepositoryPort;
import com.yers7.auth_service.domain.exception.InvalidCredentialsException;
import com.yers7.auth_service.domain.exception.UserAlreadyExistsException;
import com.yers7.auth_service.domain.model.RefreshToken;
import com.yers7.auth_service.domain.model.Role;
import com.yers7.auth_service.domain.model.TokenPair;
import com.yers7.auth_service.domain.model.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@RequiredArgsConstructor 
public class AuthService implements LoginUseCase,RegisterUseCase{

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenProviderPort tokenProviderPort;
    private final UserEventPublisherPort userEventPublisherPort;
    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
  
   

    @Override 
    public TokenPair login(String email,String password){

        User user = userRepositoryPort.findByEmail(email)
            .orElseThrow(() -> new UserAlreadyExistsException("exixting user in the database"));
    

        if(!passwordEncoderPort.matches(password,user.getPassword())){
         throw new InvalidCredentialsException("incorret password");
        }

        String accesstoken = tokenProviderPort.generatedAccessToken(user.getEmail());
        String refreshToken = tokenProviderPort.generatedRefreshToken(user.getEmail());

        RefreshToken refreshTokenEncode =  RefreshToken.builder()
        .token(refreshToken)
        .user(user)
        .issueAt(Instant.now())
        .expiresAt(Instant.now().plus(7,ChronoUnit.DAYS))
        .familyId(UUID.randomUUID())
        .replaceByTokenId(UUID.randomUUID())
        .revokedAt(null)
        .id(UUID.randomUUID())
        .build();

        refreshTokenRepositoryPort.save(refreshTokenEncode);

        return new TokenPair(accesstoken, refreshToken);    
    }

    @Override 
    public TokenPair register(String name,String email,String password){

        userRepositoryPort.findByEmail(email)
        .ifPresent((user) -> {
          throw new  UserAlreadyExistsException("exixting user in the database");
        });

        String encodedPassword = passwordEncoderPort.encode(password);

        User newUser = User.builder()
        .id(null)
        .createdAt(Instant.now())
        .email(email)
        .name(name)
        .rol(Role.USER)
        .password(encodedPassword)
        .build();

        
       User savedUser = userRepositoryPort.save(newUser);
        userEventPublisherPort.publishUserRegisterEvent(savedUser);
     
         String accesstoken = tokenProviderPort.generatedAccessToken(email);
        String refreshToken = tokenProviderPort.generatedRefreshToken(email);

        RefreshToken refreshTokenEncode =  RefreshToken.builder()
        .token(refreshToken)
        .user(savedUser)
        .issueAt(Instant.now())
        .expiresAt(Instant.now().plus(7,ChronoUnit.DAYS))
        .familyId(UUID.randomUUID())
        .replaceByTokenId(UUID.randomUUID())
        .revokedAt(null)
        .id(UUID.randomUUID())
        .build();

        refreshTokenRepositoryPort.save(refreshTokenEncode);

        return new TokenPair(accesstoken,refreshToken);
         
    }
}

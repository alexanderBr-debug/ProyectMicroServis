package com.yers7.auth_service.infrastructure.adapters.out.security.jwt;

import java.security.Key;
import java.util.Date;



import org.springframework.stereotype.Component;

import com.yers7.auth_service.application.ports.out.TokenProviderPort;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class JwtTokenProviderAdapter implements TokenProviderPort {

     private final JwtProperties jwtProperties;
    
    

     @Override
    public String generatedAccessToken(String email){

        
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());

        return  Jwts.builder()
        .setSubject(email)
        .claim("tipo_token", "ACCESS")
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + (15L * 60 * 1000)))
        .signWith(key,SignatureAlgorithm.HS256)
        .compact();
    }

    @Override 
    public String generatedRefreshToken(String email){

         Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());

        return  Jwts.builder()
        .setSubject(email)
        .claim("tipo_token", "REFRESH")
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
        .signWith(key,SignatureAlgorithm.HS256)
        .compact();
    }

    private Claims extraerClaims(String token){
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
        return Jwts.parserBuilder()
        //comparamos si tiene nuestra firma
            .setSigningKey(key)
            .build()
            //compara el tken pasado con el nuevo
            .parseClaimsJws(token)
            
            //si todo esta bien extraemos los datos del cuerpo
            .getBody();
    }

    public String obtenerTipoToken(String token){
         Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());

        return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .get("tipo_token",String.class);
    }


    @Override
    public String extractUsername(String token){
        return extraerClaims(token).getSubject();
    }

    @Override
    public boolean isValidToken(String token,String emailEsperado){
        try{
            Claims claims = extraerClaims(token);
            String emailDelToken = claims.getSubject();
            boolean noEstaExpirado = !claims.getExpiration().before(new Date());
            return emailDelToken.equals(emailEsperado) && noEstaExpirado;
        }
        catch (Exception e){
            return false;
        }

    }
}

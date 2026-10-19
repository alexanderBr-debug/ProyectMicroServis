package com.yers7.auth_service.domain.model;


import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class TokenPair {

    public TokenPair(){}
     
    public TokenPair(String accessToken,String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
   
    private String accessToken;
    private String refreshToken;
}

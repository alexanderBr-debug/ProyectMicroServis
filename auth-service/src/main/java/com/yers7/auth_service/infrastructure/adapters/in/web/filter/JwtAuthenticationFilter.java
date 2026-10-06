package com.yers7.auth_service.infrastructure.adapters.in.web.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yers7.auth_service.infrastructure.adapters.out.security.jwt.JwtTokenProviderAdapter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    

    private final JwtTokenProviderAdapter jwtTokenProviderAdapter;
    private final UserDetailsService userDetailsService;

    @Override 
    protected  void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    )throws ServletException,IOException{

       /*extraemos el encabezado del token  */ 

       final String authHeader = request.getHeader("Authorization");
       final String jwt;
       final String userEmail;

       if(authHeader == null || !authHeader.startsWith("Bearer")){
        filterChain.doFilter(request, response);
        return;
       }

  
      
       jwt = authHeader.substring(7);
       userEmail =jwtTokenProviderAdapter.extractUsername(jwt);

       if (userEmail == null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

        if(jwtTokenProviderAdapter.isValidToken(jwt,userDetails.getUsername())){

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            
        }
        
       }

       filterChain.doFilter(request, response);
    }

    @Override 
    protected boolean shouldNotFilter(HttpServletRequest request)throws ServletException{
        String path = request.getRequestURI();
        return path.startsWith("/auth");
    }

    
}



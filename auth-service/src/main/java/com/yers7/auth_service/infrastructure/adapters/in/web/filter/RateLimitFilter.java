package com.yers7.auth_service.infrastructure.adapters.in.web.filter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component 
@RequiredArgsConstructor 
public class RateLimitFilter extends  OncePerRequestFilter{

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

   
    private Bucket createNewBucket(){
        
       
        Refill refill = Refill.greedy(20, Duration.ofMinutes(1));
       
        Bandwidth limit = Bandwidth.classic(20, refill);
        return Bucket.builder().addLimit(limit).build();
    }

    private Bucket resolveBucket(String ip){
        return cache.computeIfAbsent(ip, k -> createNewBucket());
    }

    @Override 
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                     FilterChain filterChain)
    throws ServletException,IOException{

        String ip = obtenerIpReal(request);
        Bucket
         bucket = resolveBucket(ip);

        if (bucket.tryConsume(1)){
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("error 429: Too many request. Has superado el limite");
        }                                    
    }

    private String obtenerIpReal(HttpServletRequest request){
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty()) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0].trim();
    }



}

package com.capstone.newsapp.filter;

/*
 * create a class AppFilter to filter the request and response
 * use @Component annotation to make it a bean
 * extend OncePerRequestFilter and override doFilterInternal method
 * get toekn from the request header and validate the token using jwt.parse method
 * except for endpoints /api/v1/user/save and /api/v1/user/login
 * if token valid sent use in token to the request and proceed
 * else throw exception
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class AppFilter extends OncePerRequestFilter {

    private static final List<String> AUTH_WHITELIST = Arrays.asList("/api/v1/user/save", "/api/v1/user/login");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (AUTH_WHITELIST.stream().anyMatch(uri -> requestURI.contains(uri))) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new ServletException("Invalid token");
        }
        try {
            Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token.replace("Bearer ", "")).getBody();
            request.setAttribute("claims", claims);
        } catch (Exception exception) {
            throw new ServletException("Invalid token");
        }
        filterChain.doFilter(request, response);
    }

}


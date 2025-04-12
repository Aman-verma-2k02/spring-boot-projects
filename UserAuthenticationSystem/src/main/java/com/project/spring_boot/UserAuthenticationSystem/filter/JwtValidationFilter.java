package com.project.spring_boot.UserAuthenticationSystem.filter;

import com.project.spring_boot.UserAuthenticationSystem.model.Credential;
import com.project.spring_boot.UserAuthenticationSystem.model.UserPrincipal;
import com.project.spring_boot.UserAuthenticationSystem.repo.UserCredRepo;
import com.project.spring_boot.UserAuthenticationSystem.service.JwtService;
import com.project.spring_boot.UserAuthenticationSystem.service.UserCredService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserCredRepo userCredRepo;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            if(jwtService.isValidToken(jwtToken)) {
                username = jwtService.extractUsername(jwtToken);
            }
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Credential> userPrincipal =  userCredRepo.findByUsername(username);
            if(userPrincipal.isPresent()) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userPrincipal.get(), null, Collections.singleton(new SimpleGrantedAuthority("USER")));
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

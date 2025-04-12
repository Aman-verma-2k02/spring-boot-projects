package com.project.spring_boot.UserAuthenticationSystem.service;

import com.project.spring_boot.UserAuthenticationSystem.model.Credential;
import com.project.spring_boot.UserAuthenticationSystem.model.UserPrincipal;
import com.project.spring_boot.UserAuthenticationSystem.repo.UserCredRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCredService implements UserDetailsService {
    @Autowired
    private UserCredRepo userCredRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Credential> credential = userCredRepo.findByUsername(username);
        if(!credential.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(credential.get());
    }
}

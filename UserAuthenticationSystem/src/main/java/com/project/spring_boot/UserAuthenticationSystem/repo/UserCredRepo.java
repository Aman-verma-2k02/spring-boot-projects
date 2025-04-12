package com.project.spring_boot.UserAuthenticationSystem.repo;

import com.project.spring_boot.UserAuthenticationSystem.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredRepo extends JpaRepository<Credential, Integer> {

    Optional<Credential> findByUsername(String username);
}

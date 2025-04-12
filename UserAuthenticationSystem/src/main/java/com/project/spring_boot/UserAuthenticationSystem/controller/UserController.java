package com.project.spring_boot.UserAuthenticationSystem.controller;

import com.project.spring_boot.UserAuthenticationSystem.dto.UserLoginDTO;
import com.project.spring_boot.UserAuthenticationSystem.dto.UserRegisterDTO;
import com.project.spring_boot.UserAuthenticationSystem.dto.UserUpdateDTO;
import com.project.spring_boot.UserAuthenticationSystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private AuthenticationManager authManager;

    public UserController(AuthenticationManager authManager, UserService userService) {
        this.authManager = authManager;
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        try {
            userService.register(userRegisterDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Welcome " + userRegisterDTO.getFirstName() + ", Have a nice day!!");
    }

    @GetMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        Authentication auth = new UsernamePasswordAuthenticationToken
                (userLoginDTO.getUsername(), userLoginDTO.getPassword());
        Authentication newAuth = authManager.authenticate(auth);

        String message = userService.generateUserToken(userLoginDTO.getUsername());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/auth/validate")
    public ResponseEntity<?> validate(HttpServletRequest request) {
        return ResponseEntity.ok("Token validated successfully");
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<?> updateDetails(@PathVariable int userId, @RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            userService.update(userId, userUpdateDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User details updated successfully");
    }
}

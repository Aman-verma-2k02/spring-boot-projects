package com.amverma.user_service.controller;

import com.amverma.user_service.dto.UserRequestDTO;

import com.amverma.user_service.service.UserService;
import com.amverma.user_service.util.ApiResponse;
import com.amverma.user_service.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/internal")
    public ResponseEntity<?> addNewUsers(@RequestBody List<UserRequestDTO> userRequestDTOList) {
        return ResponseEntity.ok(ApiResponse.success(userService.addNewUsers(userRequestDTOList), CommonConstants.USER_REGISTERED));
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(userService.addNewUser(userRequestDTO), CommonConstants.USER_REGISTERED));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.success(userService.getAllUsers(), CommonConstants.USER_FETCHED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.success(userService.getUserById(id), CommonConstants.USER_FETCHED));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable int id, @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(userService.updateUserById(id, userRequestDTO), CommonConstants.USER_UPDATED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.success(userService.deleteUserById(id), CommonConstants.USER_DELETED));
    }
}

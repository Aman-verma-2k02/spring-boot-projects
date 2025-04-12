package com.project.spring_boot.UserAuthenticationSystem.service;

import com.project.spring_boot.UserAuthenticationSystem.dto.UserRegisterDTO;
import com.project.spring_boot.UserAuthenticationSystem.dto.UserUpdateDTO;
import com.project.spring_boot.UserAuthenticationSystem.model.Credential;
import com.project.spring_boot.UserAuthenticationSystem.model.UserInfo;
import com.project.spring_boot.UserAuthenticationSystem.repo.UserCredRepo;
import com.project.spring_boot.UserAuthenticationSystem.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserCredRepo userCredRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public JwtService jwtService;
    public void register(UserRegisterDTO userRegisterDTO) throws Exception {
        Optional<Credential> prevCred = userCredRepo.findByUsername(userRegisterDTO.getUsername());
        if(prevCred.isPresent()) {
            throw new Exception("Username already occupied");
        }
        UserInfo user = new UserInfo(userRegisterDTO);

        Credential credential = new Credential();
        credential.setUsername(userRegisterDTO.getUsername());
        credential.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        user.setUserCred(credential);
        userRepo.save(user);
    }

    public String generateUserToken(String username) {
        StringBuilder result = new StringBuilder("Hello ");
        String firstName = userRepo.findFirstNameByUserName(username);
        String jwtToken = jwtService.generateJwtToken(username);
        result.append(firstName);
        result.append(" , Here is your token: ");
        result.append(jwtToken);
        return result.toString();
    }

    public void update(int userId, UserUpdateDTO userUpdateDTO) throws Exception {
        Optional<UserInfo> userInfo = userRepo.findById(userId);
        if(!userInfo.isPresent()) {
            throw  new Exception("Invalid user id provided");
        }
        userInfo.get().updateDetails(userUpdateDTO);
        userRepo.save(userInfo.get());
    }
}

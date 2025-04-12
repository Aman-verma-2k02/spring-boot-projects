package com.project.spring_boot.UserAuthenticationSystem.model;

import com.project.spring_boot.UserAuthenticationSystem.dto.UserLoginDTO;
import com.project.spring_boot.UserAuthenticationSystem.dto.UserRegisterDTO;
import com.project.spring_boot.UserAuthenticationSystem.dto.UserUpdateDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cred_id")
    private Credential userCred;

    public UserInfo() {
    }

    public UserInfo(String firstName, String lastName, String email, String contactNo, Credential userCred) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.userCred = userCred;
    }

    public UserInfo(UserRegisterDTO userRegisterDTO) {
        this.firstName = userRegisterDTO.getFirstName();
        this.lastName = userRegisterDTO.getLastName();
        this.email = userRegisterDTO.getEmail();
        this.contactNo = userRegisterDTO.getContactNo();
    }

    public void updateDetails(UserUpdateDTO userUpdateDTO) {
        Optional.ofNullable(userUpdateDTO.getFirstName()).ifPresent(this::setFirstName);
        Optional.ofNullable(userUpdateDTO.getLastName()).ifPresent(this::setLastName);
        Optional.ofNullable(userUpdateDTO.getEmail()).ifPresent(this::setEmail);
        Optional.ofNullable(userUpdateDTO.getContactNo()).ifPresent(this::setContactNo);

    }

    @PrePersist
    void setDate() {
        this.createdOn = LocalDateTime.now();
    }
    @PreUpdate
    void updateTime() {
        this.lastUpdatedOn = LocalDateTime.now();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Credential getUserCred() {
        return userCred;
    }

    public void setUserCred(Credential userCred) {
        this.userCred = userCred;
    }
}

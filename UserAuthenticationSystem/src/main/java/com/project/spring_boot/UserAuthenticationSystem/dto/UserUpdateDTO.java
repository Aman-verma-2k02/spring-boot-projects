package com.project.spring_boot.UserAuthenticationSystem.dto;

public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(String firstName, String lastName, String email, String contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
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
}

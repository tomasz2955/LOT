package com.example.LOT;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class RegisterUserDto {
    private String fullName;
    @Email(message = "Email should be valid")
    private String email;
    private String phoneNumber;
    @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters")
    private String password;

    public RegisterUserDto(String fullName, String email, String phoneNumber, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public RegisterUserDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

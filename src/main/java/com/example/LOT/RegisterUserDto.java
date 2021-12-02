package com.example.LOT;


import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUserDto {
    @NotEmpty
    @Size(min = 1, max = 200, message = "Password must be between 1 and 200 characters")
//    @Pattern("we_qwe") regexp
    private String fullName;
    @NotEmpty
    @Email(message = "Email should be valid")
    @Size(min = 2, max = 20, message = "Password must be between 2 and 20 characters")
    private String email;
    @NotEmpty
    @Size(min = 9, max = 9)
    private String phoneNumber;
    @NotEmpty
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
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

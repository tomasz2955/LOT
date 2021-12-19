package com.example.LOT.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUserDto {
    @NotEmpty
    @Email(message = "Email should be valid")
    @Size(min = 2, max = 20, message = "Password must be between 2 and 20 characters")
    String email;
    @NotEmpty
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    String password;

    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginUserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

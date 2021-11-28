package com.example.LOT;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class LoginUserDto {
    @Email(message = "Email should be valid")
    String email;
    @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters")
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

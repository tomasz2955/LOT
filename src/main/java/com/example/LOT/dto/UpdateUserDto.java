package com.example.LOT.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateUserDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Size(min = 9, max = 9)
    private String phoneNumber;
    @NotEmpty
    @Size(min = 1, max = 200, message = "Password must be between 1 and 200 characters")
    private String password;

    public UpdateUserDto(String name, String lastName, String phoneNumber, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

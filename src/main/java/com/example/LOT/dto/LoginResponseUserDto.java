package com.example.LOT.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class LoginResponseUserDto {
    private LocalDateTime localDate = LocalDateTime.now().plusHours(3);
    private Long id;

    public LoginResponseUserDto() {
    }

    public LoginResponseUserDto(LocalDateTime localDate, Long id) {
        this.localDate = localDate;
        this.id = id;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDateTime localDate) {

        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

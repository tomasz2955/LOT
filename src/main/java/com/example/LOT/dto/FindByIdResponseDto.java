package com.example.LOT.dto;
import com.example.LOT.entity.Ticket;
import java.util.List;

public class FindByIdResponseDto {
        private Long id;
        private String name;
        private String lastName;
        private String email;
        private String phoneNumber;
        private List<Ticket> tickets;

    public FindByIdResponseDto(Long id, String name, String lastName, String email, String phoneNumber, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tickets = tickets;
    }

    public FindByIdResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}




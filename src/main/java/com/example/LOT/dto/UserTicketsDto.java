package com.example.LOT.dto;


import com.example.LOT.entity.Ticket;


import java.util.List;

public class UserTicketsDto {

    private Long id;
    private List<Ticket> tickets;

    public UserTicketsDto(Long id, List<Ticket> tickets) {
        this.id = id;
        this.tickets = tickets;
    }

    public UserTicketsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}

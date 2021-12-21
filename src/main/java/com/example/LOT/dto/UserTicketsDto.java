package com.example.LOT.dto;

import com.example.LOT.entity.Ticket;

import java.util.List;

public class UserTicketsDto {

    private Long userId;
    private List<TicketDto> tickets; //TicketDto

    public UserTicketsDto(Long userId, List<TicketDto> tickets) {
        this.userId = userId;
        this.tickets = tickets;
    }
}

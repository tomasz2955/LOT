package com.example.LOT.dto;

public class ReturnTicketDto {
    private Long ticketId; // moge przekazać nulla
    private Long passengerId; //moge przekazać nulla

    public ReturnTicketDto() {
    }

    public ReturnTicketDto(Long ticketId, Long passengerId) {
        this.ticketId = ticketId;
        this.passengerId = passengerId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }
}

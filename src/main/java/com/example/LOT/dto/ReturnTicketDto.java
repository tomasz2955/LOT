package com.example.LOT.dto;

import javax.validation.constraints.NotEmpty;

public class ReturnTicketDto {
    @NotEmpty
    private Long ticketId;
    @NotEmpty
    private Long passengerId;

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

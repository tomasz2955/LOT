package com.example.LOT.dto;

public class BuyingTicketDto {
    private Long userId;
    private Long flightId;
    private Long numberOfTickets;

    public BuyingTicketDto(Long userId, Long flightId, Long numberOfTickets) {
        this.userId = userId;
        this.flightId = flightId;
        this.numberOfTickets = numberOfTickets;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Long numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public BuyingTicketDto() {
    }
}

package com.example.LOT.dto;

public class BuyingTicketDto {
    private Long userId;
    private Long flightId;

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

    public BuyingTicketDto(Long userId, Long flightId) {
        this.userId = userId;
        this.flightId = flightId;
    }

    public BuyingTicketDto() {
    }
}

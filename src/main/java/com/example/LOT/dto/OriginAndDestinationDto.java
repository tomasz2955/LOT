package com.example.LOT.dto;

public class OriginAndDestinationDto {
    private String origin;
    private String destination;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public OriginAndDestinationDto(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public OriginAndDestinationDto() {
    }
}

package com.example.LOT;

public class FlightNotFoundException extends RuntimeException {

    private final Code code = Code.FLIGHT_NOT_FOUND;

    public Code getCode() {
        return code;
    }
}

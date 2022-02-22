package com.example.LOT;

public class TicketNotFoundException extends RuntimeException {

    private final Code code = Code.TICKET_NOT_FOUND;

    public Code getCode() {
        return code;
    }
}

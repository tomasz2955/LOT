package com.example.LOT.controllers;

import com.example.LOT.service.TicketService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    //potrzebuję endpoint który na podstawie id użytkownika zwróci mi jego wszystkie bilety

    //potrzebuję endpointu który umożliwi mi kupno biletu na podstawie id usera i id lotu

    //potrzebuję endpointu do zwrotu biletu, w logice trzeba dodać sprawdzenie czy lot nie jest za wczesniej niż 24h, jeżeli tak to rzucić exception ze zwrot juz jest niemożliwy
}

package com.example.LOT.controllers;

import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.service.TicketService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping("/tickets")
    public List<Ticket> findAll() {
        return ticketService.getTickets();
    }

    //potrzebuję endpoint który na podstawie id użytkownika zwróci mi jego wszystkie bilety

    //potrzebuję endpointu który umożliwi mi kupno biletu na podstawie id usera i id lotu

    //potrzebuję endpointu do zwrotu biletu, w logice trzeba dodać sprawdzenie czy lot nie jest za wczesniej niż 24h, jeżeli tak to rzucić exception ze zwrot juz jest niemożliwy
}

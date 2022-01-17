package com.example.LOT.controllers;

import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.RegisterUserDto;
import com.example.LOT.dto.UserTicketsDto;
import com.example.LOT.entity.Ticket;
import com.example.LOT.service.TicketService;
import com.example.LOT.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;

    public TicketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }


    @GetMapping()
    public List<Ticket> findAll() {
        return ticketService.getTickets();
    }

    @GetMapping("/{id}")
    public UserTicketsDto findTicketById(@PathVariable Long id) {
        return userService.findTicketById(id);
    }

    @PostMapping("/buy")
    public void buyTicket(@RequestBody BuyingTicketDto buyingTicketDto) {
        ticketService.buyTicket(buyingTicketDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTicketById(@PathVariable Long id) {
        ticketService.deleteById(id);
    }

}

package com.example.LOT.service;

import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.RegisterUserDto;
import com.example.LOT.dto.UserTicketsDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TicketService {


    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper mapper = new ModelMapper();


    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, FlightRepository flightRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public void buyTicket(BuyingTicketDto buyingTicketDto) {
        User findUser = userRepository.findById(buyingTicketDto.getUserId()).orElseThrow();
        Flight findFlight = flightRepository.findById(buyingTicketDto.getFlightId()).orElseThrow();
        ticketRepository.save(new Ticket(findUser, findFlight, LocalDateTime.now(), 9000.25));
    }

    public void deleteById(Long id) {
        Ticket findTicket = ticketRepository.findById(id).orElseThrow();
        if(findTicket.getFlight().getDate().isEqual(LocalDate.now())) {
            throw new RuntimeException("You can not return ticket");
        } else {

            ticketRepository.deleteById(id);
        }
    }






}

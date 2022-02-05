package com.example.LOT.service;

import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.ReturnTicketDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.PassengerRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    public static final int MAX_HOURS_BEFORE_DEPARTURE = 24;

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, FlightRepository flightRepository, PassengerRepository passengerRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }


    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public void buyTicket(BuyingTicketDto buyingTicketDto) {
        User findUser = userRepository.findById(buyingTicketDto.getUserId()).orElseThrow();
        Flight findFlight = flightRepository.findById(buyingTicketDto.getFlightId()).orElseThrow();
        Long ticketsLeft = findFlight.getAvailableTickets();
        if (ticketsLeft >= buyingTicketDto.getPassengers().size()) {
            for (int i = 0; i < buyingTicketDto.getPassengers().size(); i++) {
                ticketRepository.save(new Ticket(buyingTicketDto.getUserId(), buyingTicketDto.getPassengers().get(i), findFlight, LocalDateTime.now(), i + 1L));
            }
            findFlight.setAvailableTickets(ticketsLeft - buyingTicketDto.getPassengers().size());
            flightRepository.save(findFlight);
        } else {
            throw new RuntimeException("Not enough tickets");
        }
    }


    @Transactional
    public void deleteTicket(ReturnTicketDto returnTicketDto) {
        Ticket findTicket = ticketRepository.findById(returnTicketDto.getTicketId()).orElseThrow();
        if (returnTicketDto.getPassengerId().equals(findTicket.getPassenger().getId())) {
            LocalDateTime flightDepartureDate = findTicket.getFlight().getDepartureDate();
            LocalDateTime currentTime = LocalDateTime.now();
            Duration duration = Duration.between(currentTime, flightDepartureDate);
            if(duration.toHours() <=MAX_HOURS_BEFORE_DEPARTURE) {
                throw new RuntimeException("Ticket cannot be returned, departure time is less than 24 hours");
            } else {
                findTicket.getFlight().setAvailableTickets(findTicket.getFlight().getAvailableTickets()+1);
                ticketRepository.deleteById(returnTicketDto.getTicketId());

            }
        } else {
            throw new RuntimeException("There is no ticket in the name of this passenger");
        }
    }
}
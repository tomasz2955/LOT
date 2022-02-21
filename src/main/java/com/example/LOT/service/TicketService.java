package com.example.LOT.service;

import com.example.LOT.FlightNotFoundException;
import com.example.LOT.UserNotFoundException;
import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.ReturnTicketDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Passenger;
import com.example.LOT.entity.Ticket;
import com.example.LOT.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;


@Service
public class TicketService {
    public static final int MAX_HOURS_BEFORE_DEPARTURE = 24;

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;


    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, FlightRepository flightRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }


    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }



    @Transactional
    public Ticket buyTicket(BuyingTicketDto buyingTicketDto) {
        if (userRepository.findById(buyingTicketDto.getUserId()).isPresent()) {
            Flight flight = flightRepository.findById(buyingTicketDto.getFlightId()).orElseThrow(FlightNotFoundException::new);

            for(Passenger passengers : buyingTicketDto.getPassengers()) {
                if(!flight.isSeatTaken(passengers.getSeatNumber())) {
                        Ticket ticket = new Ticket(buyingTicketDto.getUserId(), passengers, flight, LocalDateTime.now(), passengers.getSeatNumber());
                        if(LocalDateTime.now().getDayOfWeek()==MONDAY) {
                            ticket.setPrice(flight.getPrice()-(flight.getPrice()*0.2));
                        }
                        ticketRepository.save(ticket);
                        flight.setSeatBusy(passengers.getSeatNumber(), passengers.getId());
                        return ticket;
                    } else {
                    throw new RuntimeException();
                       // throw new RuntimeException("At least one of the selected seats is already taken");
                    }
                }
        } else {
            throw new UserNotFoundException();
        }
        return null; //tym sie nie przejmuj tu nigdy sie nie pojawi ale java wymusza dodanie
    }


    @Transactional
    public void deleteTicket(ReturnTicketDto returnTicketDto) {
        Ticket ticket = ticketRepository.findById(returnTicketDto.getTicketId()).orElseThrow(UserNotFoundException::new);
        if (returnTicketDto.getPassengerId().equals(ticket.getPassenger().getId())) {
            LocalDateTime flightDepartureDate = ticket.getFlight().getDepartureDate();
            LocalDateTime currentTime = LocalDateTime.now();
            Duration duration = Duration.between(currentTime, flightDepartureDate);
            if(duration.toHours() <=MAX_HOURS_BEFORE_DEPARTURE) {
                throw new RuntimeException("Ticket cannot be returned, departure time is less than 24 hours");
            } else {
                Flight flight = flightRepository.findById(ticket.getFlight().getId()).orElseThrow(FlightNotFoundException::new);
                flight.setSeatFree(returnTicketDto.getPassengerId());
                ticketRepository.deleteById(returnTicketDto.getTicketId());
            }
        } else {
            throw new UserNotFoundException();
        }
    }



}

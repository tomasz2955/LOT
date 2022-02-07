package com.example.LOT.service;

import com.example.LOT.UserNotFoundException;
import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.ReturnTicketDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Seat;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.repository.*;
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
    private final SeatRepository seatRepository; //combo, swieci na szaro i zółto

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, FlightRepository flightRepository, SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
    }


    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }


    @Transactional
    public void buyTicket(BuyingTicketDto buyingTicketDto) {
        User findUser = userRepository.findById(buyingTicketDto.getUserId()).orElseThrow(UserNotFoundException::new); //to swieci na szaro, to faul jak podswietlenie na zółto
        Flight flight = flightRepository.findById(buyingTicketDto.getFlightId()).orElseThrow();

        if (flight.getSeats().size() >= buyingTicketDto.getPassengers().size()) { //te sprawdzenie mialo sens przy wczesniejszej implementacji. teraz jak bookujemy konkretne miejsca to juz nie jest potrzebne
            for (int i = 0; i < buyingTicketDto.getPassengers().size(); i++) { //lepiej zrobic for eacha zamiast fora z iteratorem, wtedy nie musisz przekazywac indeksu
                if (!flight.isSeatTaken(buyingTicketDto.getPassengers().get(i).getSeatNumber())) {
                    ticketRepository.save(new Ticket(buyingTicketDto.getUserId(), buyingTicketDto.getPassengers().get(i), flight, LocalDateTime.now(), buyingTicketDto.getPassengers().get(i).getSeatNumber()));
                    flight.setSeatBusy(buyingTicketDto.getPassengers().get(i).getSeatNumber(), buyingTicketDto.getPassengers().get(i).getId());
                } else {
                    throw new RuntimeException("At least one of the selected seats is already taken"); // lenistwo lenistwo
                }
            }
        }  else {
            throw new RuntimeException("Not enough free seats"); // lenistwo lenistwo
        }

    }





    @Transactional
    public void deleteTicket(ReturnTicketDto returnTicketDto) {
        Ticket findTicket = ticketRepository.findById(returnTicketDto.getTicketId()).orElseThrow(UserNotFoundException::new); //nazwij zmienną po prostu ticket ewentualnie foundTicket
        if (returnTicketDto.getPassengerId().equals(findTicket.getPassenger().getId())) {
            LocalDateTime flightDepartureDate = findTicket.getFlight().getDepartureDate();
            LocalDateTime currentTime = LocalDateTime.now();
            Duration duration = Duration.between(currentTime, flightDepartureDate);
            if(duration.toHours() <=MAX_HOURS_BEFORE_DEPARTURE) {
                throw new RuntimeException("Ticket cannot be returned, departure time is less than 24 hours");
            } else {

                ticketRepository.deleteById(returnTicketDto.getTicketId());
                //findTicket.getFlight().setSeatFree(returnTicketDto.getPassengerId());

            }
        } else {
            throw new UserNotFoundException();
        }
    }



}

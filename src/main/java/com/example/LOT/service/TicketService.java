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
    private final PassengerRepository passengerRepository; // coś tu się świeci jak reflektor

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
        User findUser = userRepository.findById(buyingTicketDto.getUserId()).orElseThrow(); //chyba nieuzwane, bardzo nie łądnie
        Flight findFlight = flightRepository.findById(buyingTicketDto.getFlightId()).orElseThrow(); //nazwalbym property po prostu flight
        Long ticketsLeft = findFlight.getAvailableTickets(); //nie ma sensu przypisywac property to property, uzyj getAvailableTickets bezposrednio w pętli
        if (ticketsLeft >= buyingTicketDto.getPassengers().size()) { //obecnie mogę przekazać pustą listę pasażerów, wtedy kod sie wykona poprawnie ale zaden ticket nie powstanie
            for (int i = 0; i < buyingTicketDto.getPassengers().size(); i++) {
                //przy danych pasazera powinna byc przekazywana informacja o wybranym miejscu siedzącym zamiast przydzielać je losowo jak jest teraz
                //wychodziloby na to że Flight powinien mieć listę encji Seat - siedzenie zawierałoby swój id, nazwę miejsca (C9, D1 itp) i id pasażera
                // podczas dodawania pasażera trzebaby sprawdzić czy to miejsce nie jest przypadkiem już zajęte
                // czy jest zajęte będziemy wiedzieli po tym czy id pasażera jest nullem czy nie
                // wtedy możemy usunąć pole available tickets, żeby wiedzieć czy jeszcze są bilety będziemy musieli iterować się przez listę seats i patrzeć czy są wolne
                ticketRepository.save(new Ticket(buyingTicketDto.getUserId(), buyingTicketDto.getPassengers().get(i), findFlight, LocalDateTime.now(), i + 1L));
            }
            findFlight.setAvailableTickets(ticketsLeft - buyingTicketDto.getPassengers().size());
            flightRepository.save(findFlight);
        } else {
            throw new RuntimeException("Not enough tickets"); //leniwcu, mialeś robić exceptiony dla danych przypadków
        }
    }


    @Transactional
    public void deleteTicket(ReturnTicketDto returnTicketDto) {
        Ticket findTicket = ticketRepository.findById(returnTicketDto.getTicketId()).orElseThrow(); //orelsetrhow bez exceptiona, nie ładnie
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
            throw new RuntimeException("There is no ticket in the name of this passenger"); //leniwcu, mialeś robić exceptiony dla danych przypadków
        }
    }
}

package com.example.LOT.service;

import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {


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

    public void buyTicket(BuyingTicketDto buyingTicketDto) {
        User findUser = userRepository.findById(buyingTicketDto.getUserId()).orElseThrow(); //orElseThrow musi wszędzie rzucac jakis exception z message który mi cos powie na frontendzie
        Flight findFlight = flightRepository.findById(buyingTicketDto.getFlightId()).orElseThrow(); //to samo
        Long ticketsLeft = findFlight.getAvailableTickets();
        if(ticketsLeft>= buyingTicketDto.getNumberOfTickets()) {
            for(int i=1;i<=buyingTicketDto.getNumberOfTickets();i++) {
                ticketRepository.save(new Ticket(findUser, findFlight, LocalDateTime.now()));
                //myslalem ze bedziemy zapisywac jeden ticket ktory zawieralby informacje o ilosci sztuk
                //ale jak tak zrobiles to sensownie byloby jakbym przekazywal ci dane osobowe poszczegolnych podrozujacych. Wtedy
                //kazdy ticket mialby informacje o osobie ktora leci zamiast duplikowac info o kupującym na kazdym bilecie
                //Z buyingTicketDto wylatuje number of tickets, zamiast tego przekazemy listę pasażerow
                //Zrobisz encje pasazera i bedziesz tworzyc ticket per osoba

            }
            findFlight.setAvailableTickets(ticketsLeft- buyingTicketDto.getNumberOfTickets());
            flightRepository.save(findFlight);
        } else {
            throw new RuntimeException("Not enough tickets");
        }


        //podczas tworzenia encji flight powinienes ustawiac ilosc dostępnych biletów w locie. Podczas kupowania biletów powinienes
        //odejmowac tą ilość z lotu. Gdy lot nie ma juz biletów powinieneś rzucić exception z odpowiednim message


    }

    //usuwanie biletu na podstawie ID
    public void deleteById(Long id) {
        Ticket findTicket = ticketRepository.findById(id).orElseThrow(); //oresleThrow trzeba rzucic exception z sensownym komentarzem zeby frontend wiedzial co sie stalo
        LocalDateTime from = findTicket.getFlight().getDepartureDate(); //ten opis zmiennej wiele nie mówi, to samo ponizej. Tą np nazwałbym flightDepartureDate
        LocalDateTime to = LocalDateTime.now();
        Duration duration = Duration.between(to, from);
        if(duration.toHours() <=24) { // w kodzie mowi się o czyms takim jak magic number, to są takie numery które są bezposrednio uzyte w kodzie i nie mają żadnego opisu
            //powinienes przypisac ją do zmiennej która będzie informująca: https://stackoverflow.com/questions/47882/what-is-a-magic-number-and-why-is-it-bad
            throw new RuntimeException("You can not return ticket"); // wiadomosc dla kupującego musi cos mu mówić, nie mozesz zwrócić biletu jeżeli lot jest za mniej niz 24h
        } else {
            ticketRepository.deleteById(id);
        }
    }
}

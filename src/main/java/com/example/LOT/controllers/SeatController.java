package com.example.LOT.controllers;

import com.example.LOT.entity.Passenger;
import com.example.LOT.entity.Seat;
import com.example.LOT.service.SeatService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/seat")

public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }


    @GetMapping()
    public List<Seat> findAll() {
        return seatService.getSeats();
    }

}

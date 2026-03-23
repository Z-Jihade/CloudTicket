package com.example.CloudTicket.controller;

import com.example.CloudTicket.entity.Seat;
import com.example.CloudTicket.service.ReservationService;
import com.example.CloudTicket.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class SeatController {
    private SeatService seatService;
    @Autowired
    private ReservationService reservationService;
    @GetMapping("/available")
    public List<Seat> getAvailableSeats() {
        return seatService.getAvailableSeats();
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserve(@RequestParam Long seatId,
                                     @RequestParam Long userId) {

        String result = reservationService.reserveSeat(seatId, userId);
        return ResponseEntity.ok(result);
    }
}

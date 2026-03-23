package com.example.CloudTicket.controller;

import com.example.CloudTicket.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class ReservationController {

    private ReservationService reservationService;
    /*@PostMapping("/reserve")
    public ResponseEntity<?> reserveSeats(@RequestParam Long seatId, @RequestParam Long userId) {
        reservationService.reserveSeats(seatId, userId);
        return ResponseEntity.ok("Reservation created");
    }*/
}

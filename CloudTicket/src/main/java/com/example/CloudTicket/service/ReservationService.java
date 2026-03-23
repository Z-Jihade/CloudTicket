package com.example.CloudTicket.service;

import com.example.CloudTicket.entity.Reservation;
import com.example.CloudTicket.entity.Seat;
import com.example.CloudTicket.entity.User;
import com.example.CloudTicket.repository.ReservationRepository;
import com.example.CloudTicket.repository.SeatRepository;
import com.example.CloudTicket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired private ReservationRepository reservationRepository;
    @Autowired private SeatRepository seatRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private SeatService seatService;
    @Transactional
    public String reserveSeat(Long seatId, Long userId) {
        List<Seat> lockedSeats = new ArrayList<>();
        boolean locked = seatService.lockSeat(seatId, userId);
        User user = userRepository.findById(userId).orElseThrow();
        if (!locked) {
            return "Seat already reserved!";
        }
        Seat seat = seatRepository.findById(seatId).orElseThrow();

        Reservation res = new Reservation();
        res.setSeats(lockedSeats);
        res.setUser(user);
        res.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        res.setStatus("PENDING");

        reservationRepository.save(res);

        return "Seat locked successfully!";
    }
    @Transactional
    public Reservation reserveSeats(Long userId, List<Long> seatIds) {
        List<Seat> lockedSeats = new ArrayList<>();
        User user = userRepository.findById(userId).orElseThrow();

        try {
            for (Long seatId : seatIds) {
                boolean locked = seatService.lockSeat(seatId, userId);
                if (!locked) throw new RuntimeException("Seat already reserved: " + seatId);
                lockedSeats.add(seatRepository.findById(seatId).orElseThrow());
            }

            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setSeats(lockedSeats);
            reservation.setStatus("PENDING");

            return reservationRepository.save(reservation);

        } catch (Exception e) {
            // rollback locks
            for (Seat s : lockedSeats) seatService.releaseSeat(s.getId());
            throw e;
        }
    }
}

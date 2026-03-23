package com.example.CloudTicket.repository;

import com.example.CloudTicket.entity.Seat;
import com.example.CloudTicket.entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Seat Repository
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByStatus(SeatStatus status);
}

package com.example.CloudTicket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seat {
    @Id @GeneratedValue
    private Long id;
    private String seatNumber;
    @Enumerated(EnumType.STRING) private SeatStatus status; // AVAILABLE, RESERVED, SOLD
}

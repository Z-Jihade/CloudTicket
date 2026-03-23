package com.example.CloudTicket.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long reservationId;
    private Long userId;
    private Double amount;
    private String paymentMethod;
}

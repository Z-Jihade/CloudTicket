package com.example.CloudTicket.controller;

import com.example.CloudTicket.service.PaymentLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class PaymentController {


    private PaymentLogService paymentService;
    /*@PostMapping("/pay")
    public ResponseEntity?> pay(@RequestParam Long reservationId) {
        paymentService.processPayment(reservationId);
        return ResponseEntity.ok("Payment processing");
    }
    @PostMapping("/confirm")
    public ResponseEntity?> confirm(@RequestParam Long reservationId) {
        paymentService.confirmPayment(reservationId);
        return ResponseEntity.ok("confirm message");
    }*/
}

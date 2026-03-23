package com.example.CloudTicket.service;

import com.example.CloudTicket.entity.PaymentLog;
import com.example.CloudTicket.entity.PaymentStatus;
import com.example.CloudTicket.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentLogService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentLog createLog(Long userId, Long reservationId, Double amount, String method) {
        PaymentLog log = new PaymentLog();
        log.setUserId(userId);
        log.setReservationId(reservationId);
        log.setAmount(amount);
        log.setPaymentMethod(method);
        log.setStatus(PaymentStatus.PENDING);
        return paymentRepository.save(log);
    }

    public void markSuccess(Long logId) {
        PaymentLog log = paymentRepository.findById(logId).orElseThrow();
        log.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(log);
    }

    public void markFailed(Long logId) {
        PaymentLog log = paymentRepository.findById(logId).orElseThrow();
        log.setStatus(PaymentStatus.FAILED);
        paymentRepository.save(log);

    }
}
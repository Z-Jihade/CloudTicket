package com.example.CloudTicket.repository;

import com.example.CloudTicket.entity.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentLog, Long> {
    List<PaymentLog> findByUserId(Long userId);


    List<PaymentLog> findByReservationId(Long reservationId);
}


package com.example.CloudTicket.service;


import com.example.CloudTicket.entity.Seat;
import com.example.CloudTicket.entity.SeatStatus;
import com.example.CloudTicket.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.List;

@Service
public class SeatService {

    @Autowired private SeatRepository seatRepository;
    @Autowired private StringRedisTemplate redisTemplate;

    public List<Seat> getAvailableSeats() {
        return seatRepository.findByStatus(SeatStatus.AVAILABLE);
    }

    public boolean lockSeat(Long seatId, Long userId) {
        String key = "seat:" + seatId;
        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent(key, userId.toString(), Duration.ofMinutes(10));
        return Boolean.TRUE.equals(success);
    }

    public void releaseSeat(Long seatId) {
        redisTemplate.delete("seat:" + seatId);
    }
}

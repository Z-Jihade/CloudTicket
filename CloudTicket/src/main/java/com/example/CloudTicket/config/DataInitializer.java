package com.example.CloudTicket.config;

import com.example.CloudTicket.entity.Role;
import com.example.CloudTicket.entity.Seat;
import com.example.CloudTicket.entity.SeatStatus;
import com.example.CloudTicket.entity.User;
import com.example.CloudTicket.repository.SeatRepository;
import com.example.CloudTicket.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadDemoData(
            UserRepository userRepository,
            SeatRepository seatRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setFirstName("Admin");
                admin.setLastName("Cloud");
                admin.setEmail("admin@cloudticket.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);

                User user = new User();
                user.setFirstName("Sara");
                user.setLastName("ZIDANI");
                user.setEmail("sara@cloudticket.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);

                userRepository.saveAll(List.of(admin, user));
            }

            if (seatRepository.count() == 0) {
                seatRepository.saveAll(List.of(
                        createSeat("A1", SeatStatus.AVAILABLE),
                        createSeat("A2", SeatStatus.AVAILABLE),
                        createSeat("A3", SeatStatus.RESERVED),
                        createSeat("B1", SeatStatus.AVAILABLE),
                        createSeat("B2", SeatStatus.SOLD),
                        createSeat("B3", SeatStatus.AVAILABLE)
                ));
            }
        };
    }

    private static Seat createSeat(String seatNumber, SeatStatus status) {
        Seat seat = new Seat();
        seat.setSeatNumber(seatNumber);
        seat.setStatus(status);
        return seat;
    }
}

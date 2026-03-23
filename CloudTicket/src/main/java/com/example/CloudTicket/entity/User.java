package com.example.CloudTicket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true) private String email;
    private String password;
    @Enumerated(EnumType.STRING) private Role role;
}

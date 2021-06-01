package com.example.smartparking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    // Reservation (id, number, user, parkingSpot, vehicle, issuedAt date/time,
    // expiresAt date/time, start stae/time for the reservation, end date/time for the reservation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resevationNumber;
    private LocalDateTime isuedAt;
    private LocalDateTime expiredAt;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private User user;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;


}

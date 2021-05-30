package com.example.smartparking.model;

import java.time.LocalDateTime;

public class Reservation {
    // Reservation (id, number, user, parkingSpot, vehicle, issuedAt date/time,
    // expiresAt date/time, start stae/time for the reservation, end date/time for the reservation

    private Long id;
    private String resevationNumber;
    private User user;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;
    private LocalDateTime isuedAt;
    private LocalDateTime expiredAt;
    private  LocalDateTime startTime;
    private  LocalDateTime endTime;


}

package com.example.smartparking.model;

import java.time.LocalDateTime;

public class ParkingTicket {

    // ParkingTicket(id, parkingSpot, vehicle, start date/time...)

    private Long id;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;
    private LocalDateTime startTime;
    private  LocalDateTime endTime;
}

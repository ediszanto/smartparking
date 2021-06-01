package com.example.smartparking.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@ToString
@Table(name = "reservation")
public class Reservation {
    // Reservation (id, number, user, parkingSpot, vehicle, issuedAt date/time,
    // expiresAt date/time, start stae/time for the reservation, end date/time for the reservation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_number")
    private UUID reservationNumber;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private ParkingSpot  parkingSpot;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


}

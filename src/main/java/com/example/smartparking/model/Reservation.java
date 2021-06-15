package com.example.smartparking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
//@ToString
@Table(name = "reservation")
@NoArgsConstructor
public class Reservation {
    // Reservation (id, number, user, parkingSpot, vehicle, issuedAt date/time,
    // expiresAt date/time, start stae/time for the reservation, end date/time for the reservation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_number")
    private String reservationNumber;

    @Column(name = "issued_at")
    private LocalDate issuedAt;

    @Column(name = "expires_at")
    private LocalDate expiresAt;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "start_time")
    private LocalDate startTime;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "end_time")
    private LocalDate endTime;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpot  parkingSpot;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

}

package com.example.smartparking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "parking_ticket")
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpot parkingSpot;


    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @NotNull
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private  LocalDateTime endTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_fee_id")
    private ParkingFee parkingFee;
}

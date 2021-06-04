package com.example.smartparking.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "parking_fee")
@NoArgsConstructor
public class ParkingFee {
// ParkingFee -per hour (id, parkingLot, size, amount...)
// (depending on the type of vehicle size you can have different algorithms for calculating the fee)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "parking_spot_id")
//    @OneToOne
//    @JoinColumn(name = "parking_spot_id")
//    private ParkingSpot parkingSpot;

    @NotNull
    @Column(name = "parking_spot_size")
    private String parkingSpotSize;

    @Column(name = "amount_to_charge")
    private Double amount;


}

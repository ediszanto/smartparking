package com.example.smartparking.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "parking_fee")
@NoArgsConstructor
public class ParkingFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parking_spot_size")
    private String parkingSpotSize;

    @Column(name = "amount_to_charge")
    private Double amount;


}

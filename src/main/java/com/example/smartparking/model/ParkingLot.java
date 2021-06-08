package com.example.smartparking.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String address;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "spot_id")
    private List<ParkingSpot> parkingSpots;
}

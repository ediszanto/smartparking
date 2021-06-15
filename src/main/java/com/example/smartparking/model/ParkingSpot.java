package com.example.smartparking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Table(name = "parking_spot")
@Entity
public class ParkingSpot  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long number;

    @NotNull
    @Size(max = 3)
    private String size;

    @NotNull
    private String status;

    @Column(name = "lot_id")
    private Long parkingLotId;

}

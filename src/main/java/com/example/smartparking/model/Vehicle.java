package com.example.smartparking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate")
    @NotNull
    private String licensePlate;

    @NotNull
    private String size; // S – motorcycle, M – car, L – truck, XL – bus
}

package com.example.smartparking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "parking_lot")
@NoArgsConstructor
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 10, min = 5)
    private String name;

    @NotNull
    @Size(max = 25)
    private String address;
}

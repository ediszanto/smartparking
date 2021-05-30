package com.example.smartparking.model;

public class ParkingFee {

// ParkingFee -per hour (id, parkingLot, size, amount...)
// (depending on the type of vehicle size you can have different algorithms for calculating the fee)
    private Long id;
    private ParkingLot parkingLot;
    private String size;
    private Double amount;


}

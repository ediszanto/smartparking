package com.example.smartparking.model;

public class ParkingSpot {

    private Long id;
    private Long number;
    private String type; // poate merge cu un enum
    private String size;
    private Boolean status; // asta e pt <free> din documentatie
}

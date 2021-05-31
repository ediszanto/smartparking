package com.example.smartparking.service;

import com.example.smartparking.model.ParkingSpot;
import javassist.NotFoundException;

import java.util.List;

public interface ParkingSpotService {

    ParkingSpot createParkingSpot(ParkingSpot parkingSpot);

    ParkingSpot findSpotById(Long spotId) throws NotFoundException;

    List<ParkingSpot> getAllSpots();

    void deleteSpotByNumber(Long spotNumber) throws NotFoundException;

    ParkingSpot findSpotByNumber(Long spotNumber) throws NotFoundException;

    List<ParkingSpot> findSpotsByStatus(String spotStatus);
}

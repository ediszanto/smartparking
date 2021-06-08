package com.example.smartparking.service;

import com.example.smartparking.model.ParkingSpot;
import java.util.List;

public interface ParkingSpotService {

    ParkingSpot saveParkingSpot(ParkingSpot parkingSpot);

    ParkingSpot findSpotById(Long spotId);

    List<ParkingSpot> getAllSpots();

    void deleteSpotByNumber(Long spotNumber);

    ParkingSpot findSpotByNumber(Long spotNumber);

    List<ParkingSpot> findSpotsByStatus(String spotStatus);

    ParkingSpot updateParkingSpot(ParkingSpot parkingSpot);
}

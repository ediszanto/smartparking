package com.example.smartparking.service;

import com.example.smartparking.model.ParkingLot;

public interface ParkingLotService {
    ParkingLot saveParkingLot(ParkingLot parkingLot);

    void deleteParkingLot(Long id);

    ParkingLot getById(Long id);
}

package com.example.smartparking.service.impl;

import com.example.smartparking.exceptions.NotFoundException;
import com.example.smartparking.model.ParkingLot;
import com.example.smartparking.repository.ParkingLotRepository;
import com.example.smartparking.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingLot saveParkingLot(ParkingLot parkingLot) {
        boolean exists = parkingLotRepository.findByName(parkingLot.getName()).isEmpty();
        if(!exists){
            throw new IllegalStateException("Parking Lot already created");
        }
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public void deleteParkingLot(Long id) {
        ParkingLot parkingLotToDelete = getById(id);
        parkingLotRepository.delete(parkingLotToDelete);
    }

    @Override
    public ParkingLot getById(Long id) {
        return parkingLotRepository.findById(id).orElseThrow(() -> new NotFoundException("Parking lot no found"));
    }
}

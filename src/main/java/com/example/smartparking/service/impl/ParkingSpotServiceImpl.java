package com.example.smartparking.service.impl;

import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.repository.ParkingSpotRepository;
import com.example.smartparking.service.ParkingSpotService;
import com.example.smartparking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;

    @Override
    public ParkingSpot createParkingSpot(ParkingSpot parkingSpot) {
        boolean spotExists = parkingSpotRepository.getByNumber(parkingSpot.getNumber()).isPresent();
        if (spotExists){
            throw new IllegalStateException(String.format("Spot with number {} is already created", parkingSpot.getNumber()));
        }
        return parkingSpotRepository.save(parkingSpot);
    }

    @Override
    public ParkingSpot findSpotByNumber(Long spotNumber){
        return parkingSpotRepository.getByNumber(spotNumber)
                .orElseThrow(() -> new NotFoundException("parking spot not found"));
    }

    @Override
    public void deleteSpotByNumber(Long spotNumber){
        ParkingSpot parkingSpot = findSpotByNumber(spotNumber);
        parkingSpotRepository.delete(parkingSpot);
    }

    @Override
    public List<ParkingSpot> findSpotsByStatus(String spotStatus) {
        return parkingSpotRepository.getParkingSpotByStatusSpots(spotStatus);
    }

    @Override
    public ParkingSpot updateParkingSpot(ParkingSpot parkingSpot) {
        ParkingSpot savedParkingSpot = findSpotByNumber(parkingSpot.getNumber());
        savedParkingSpot.setNumber(Optional.ofNullable(parkingSpot.getNumber()).orElse(savedParkingSpot.getNumber()));
        savedParkingSpot.setSize(Optional.ofNullable(parkingSpot.getSize()).orElse(savedParkingSpot.getSize()));
        savedParkingSpot.setStatus(Optional.ofNullable(parkingSpot.getStatus()).orElse(savedParkingSpot.getStatus()));
        return parkingSpotRepository.save(savedParkingSpot);
    }

    @Override
    public ParkingSpot findSpotById(Long spotId){
        return parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new NotFoundException("spot not found in DB"));
    }

    @Override
    public List<ParkingSpot> getAllSpots() {
        return parkingSpotRepository.findAll();
    }


}

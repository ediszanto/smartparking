package com.example.smartparking.service.impl;

import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.repository.ParkingSpotRepository;
import com.example.smartparking.service.ParkingSpotService;
import javassist.NotFoundException;
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
            throw new IllegalStateException(String.format("Spot with number %i is already created", parkingSpot.getNumber()));
        }
        return parkingSpotRepository.save(parkingSpot);
    }

    @Override
    public void deleteSpotByNumber(Long spotNumber) throws NotFoundException {
        ParkingSpot parkingSpot = findSpotByNumber(spotNumber);
        parkingSpotRepository.delete(parkingSpot);
    }

    @Override
    public ParkingSpot findSpotByNumber(Long spotNumber) throws NotFoundException {
       return parkingSpotRepository.getByNumber(spotNumber)
               .orElseThrow(() -> new NotFoundException("spot not found"));
    }

    @Override
    public List<ParkingSpot> findSpotsByStatus(String spotStatus) {
        return parkingSpotRepository.getParkingSpotByStatusSpots(spotStatus);
    }

    @Override
    public ParkingSpot updateParkingSpo(ParkingSpot parkingSpot) throws NotFoundException {
        ParkingSpot savedParkingSpot = findSpotByNumber(parkingSpot.getNumber());
        savedParkingSpot.setNumber(Optional.ofNullable(parkingSpot.getNumber()).orElse(savedParkingSpot.getNumber()));
        savedParkingSpot.setSize(Optional.ofNullable(parkingSpot.getSize()).orElse(savedParkingSpot.getSize()));
        savedParkingSpot.setStatus(Optional.ofNullable(parkingSpot.getStatus()).orElse(savedParkingSpot.getStatus()));
        return parkingSpotRepository.save(savedParkingSpot);
    }

    @Override
    public ParkingSpot findSpotById(Long spotId) throws NotFoundException {
        return parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new NotFoundException("spot not found in DB"));
    }

    @Override
    public List<ParkingSpot> getAllSpots() {
        return parkingSpotRepository.findAll();
    }


}

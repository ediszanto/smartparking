package com.example.smartparking.service.impl;

import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.repository.ParkingSpotRepository;
import com.example.smartparking.service.ParkingSpotService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;

    @Override
    public ParkingSpot createParkingSpot(ParkingSpot parkingSpot) {
        //Optional.ofNullable(parkingSpotRepository.getByNumber(parkingSpot.getNumber())).orElseThrow(() -> new IllegalStateException("Spot Already Created"));
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
    public ParkingSpot findSpotById(Long spotId) throws NotFoundException {
        return parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new NotFoundException("spot not found in DB"));
    }

    @Override
    public List<ParkingSpot> getAllSpots() {
        return parkingSpotRepository.findAll();
    }


}

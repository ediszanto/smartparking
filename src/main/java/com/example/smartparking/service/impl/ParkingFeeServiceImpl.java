package com.example.smartparking.service.impl;

import com.example.smartparking.exceptions.NotFoundException;
import com.example.smartparking.model.ParkingFee;
import com.example.smartparking.repository.ParkingFeeRepository;
import com.example.smartparking.service.ParkingFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingFeeServiceImpl implements ParkingFeeService {

    private final ParkingFeeRepository parkingFeeRepository;

    @Override
    public ParkingFee updateParkingFee(Long id, ParkingFee parkingFeeUpdated) {
        ParkingFee parkingFee = findById(id);
        parkingFee.setParkingSpotSize(Optional.ofNullable(parkingFeeUpdated.getParkingSpotSize()).orElse(parkingFee.getParkingSpotSize()));
        parkingFee.setAmount(Optional.ofNullable(parkingFeeUpdated.getAmount()).orElse(parkingFee.getAmount()));
        return parkingFeeRepository.save(parkingFee);
    }

    @Override
    public ParkingFee findById(Long id) {
        ParkingFee parkingFee = Optional.ofNullable(parkingFeeRepository.getParkingFeeById(id)).orElseThrow(() -> new NotFoundException("Parking fee not found"));
        return parkingFee;
    }
}

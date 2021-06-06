package com.example.smartparking.service;


import com.example.smartparking.model.ParkingFee;

public interface ParkingFeeService {

    ParkingFee updateParkingFee(Long id, ParkingFee parkingFee);

    ParkingFee findById(Long id);

}

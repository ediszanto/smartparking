package com.example.smartparking.controller;

import com.example.smartparking.model.ParkingLot;
import com.example.smartparking.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/smartparking")
@RequiredArgsConstructor
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @PostMapping("/lot")
    public ResponseEntity<ParkingLot> saveParkingLot(@Valid @RequestBody ParkingLot parkingLot){
        ParkingLot savedParkingLot = parkingLotService.saveParkingLot(parkingLot);
        return new ResponseEntity<>(savedParkingLot, HttpStatus.CREATED);
    }

    @DeleteMapping("/lot/{id}")
    public ResponseEntity<Void> deleteParkingLot(@PathVariable Long id){
        parkingLotService.deleteParkingLot(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}

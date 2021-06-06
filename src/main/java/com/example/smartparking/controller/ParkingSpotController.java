package com.example.smartparking.controller;

import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.service.ParkingSpotService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/smartparking")

public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

//    ADD, UPDATE, DELETE, SEARCH Parking Spot!

//    ADD
    @PostMapping("/spot")
    public ResponseEntity<ParkingSpot> saveParkingSpot(@RequestBody @Validated ParkingSpot parkingSpot){
        ParkingSpot newParkingSpot = parkingSpotService.createParkingSpot(parkingSpot);
        return new ResponseEntity<>(newParkingSpot, HttpStatus.CREATED);
    }

//    UPDTE
    @PutMapping("/spot")
    public ResponseEntity<ParkingSpot> updateParkingSpot(@Validated @RequestBody ParkingSpot   parkingSpot) {
        ParkingSpot upadtedParkingSpot = parkingSpotService.updateParkingSpot(parkingSpot);
        return new ResponseEntity<>(upadtedParkingSpot, HttpStatus.OK);
    }


//    DELETE By Spot Number
    @DeleteMapping("/spot/{spotNumber}")
    public ResponseEntity<Void> deleteParkingSpot(@PathVariable Long spotNumber) {
        parkingSpotService.deleteSpotByNumber(spotNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    FIND
//    By Number
    @GetMapping("/spot/number/{spotNumber}")
    public ResponseEntity<ParkingSpot> getSpotByNumber(@PathVariable Long spotNumber) {
        ParkingSpot spot = parkingSpotService.findSpotByNumber(spotNumber);
        return new ResponseEntity<>(spot, HttpStatus.OK);
    }

//    By Status
    @GetMapping("/spot/status/{spotStatus}")
    public ResponseEntity<List<ParkingSpot>> getSpotsByStatus(@PathVariable("spotStatus") String spotStatus){
        List<ParkingSpot> spots = parkingSpotService.findSpotsByStatus(spotStatus);
        return new ResponseEntity<>(spots, HttpStatus.OK);
    }

    @GetMapping("/spot/{spotId}")
    public ResponseEntity<ParkingSpot> getSpotById(@PathVariable Long spotId) {
        ParkingSpot spot = parkingSpotService.findSpotById(spotId);
        return new ResponseEntity<>(spot, HttpStatus.OK);
    }

    @GetMapping("/spot/all")
    public ResponseEntity<List<ParkingSpot>> getAllSpots() throws NotFoundException {
        List<ParkingSpot> spots = parkingSpotService.getAllSpots();
        return new ResponseEntity<>(spots, HttpStatus.OK);
    }

}

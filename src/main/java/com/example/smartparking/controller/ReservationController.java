package com.example.smartparking.controller;

import com.example.smartparking.model.Reservation;
import com.example.smartparking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/smartparking/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> reservationRequst(@Valid @RequestBody Reservation reservationRequest, Authentication authentication) {
        Reservation reservation = reservationService.saveReservation(reservationRequest, authentication);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id){
        Reservation reservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @Valid @RequestBody Reservation reservationUpdate) {
        Reservation reservation = reservationService.updateReservation(id, reservationUpdate);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> cancelReservaion(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

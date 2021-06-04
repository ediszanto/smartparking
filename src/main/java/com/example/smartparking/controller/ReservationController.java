package com.example.smartparking.controller;

import com.example.smartparking.model.Authority;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.service.ReservationService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/smartparking/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id){
        Reservation reservation = reservationService.getReservationByReservationNumber(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> reservationRequst(@RequestBody Reservation reservationRequest, Authentication authentication) {
        Reservation reservation = reservationService.makeReservation(reservationRequest, authentication);
        if (reservation != null) {
            log.info("Reservation Created succesfully");
        }
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservationUpdate) {
        Reservation reservation = reservationService.updateReservation(id, reservationUpdate);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> cancelReservaion(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

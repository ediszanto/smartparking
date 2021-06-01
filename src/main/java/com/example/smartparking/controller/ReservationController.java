package com.example.smartparking.controller;

import com.example.smartparking.model.Authority;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smartparking/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> reservationRequst(@RequestBody Reservation reservationRequest, Authentication authentication){
        Reservation reservation = reservationService.makeReservation(reservationRequest, authentication);
        if (reservation != null) {
            log.info("Reservation Created succesfully");
        }
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
}

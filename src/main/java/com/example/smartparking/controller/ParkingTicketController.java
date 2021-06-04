package com.example.smartparking.controller;

import com.example.smartparking.model.ParkingTicket;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.service.ParkingTicketService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/smartparking")
public class ParkingTicketController {

        private final ParkingTicketService parkingTicketService;

        @PostMapping("/park/{reservationNumber}")
        public ResponseEntity<ParkingTicket> parkWithReservation(@PathVariable String reservationNumber){
                if(reservationNumber.isEmpty()){
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                ParkingTicket newParkingticket = parkingTicketService.parkWithReservation(reservationNumber);
                return new ResponseEntity<>(newParkingticket, HttpStatus.CREATED);
        }

        @PostMapping("/park")
        public ResponseEntity<ParkingTicket> parkWithoutReservation(@RequestBody ParkingTicket parkingTicket){
                ParkingTicket newParkingTicket = parkingTicketService.parkWithoutReservation(parkingTicket);
                return new ResponseEntity<>(newParkingTicket, HttpStatus.CREATED);
        }

        @PatchMapping("/pay/{ticketId}")
        public ResponseEntity<ParkingTicket> pay(@PathVariable Long ticketId){
                ParkingTicket parkingTicket = parkingTicketService.pay(ticketId);
                return new ResponseEntity<>(HttpStatus.OK);
        }






}

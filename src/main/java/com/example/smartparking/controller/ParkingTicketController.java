package com.example.smartparking.controller;

import com.example.smartparking.service.ParkingTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/smartparking")
public class ParkingTicketController {

        private final ParkingTicketService parkingTicketService;



}

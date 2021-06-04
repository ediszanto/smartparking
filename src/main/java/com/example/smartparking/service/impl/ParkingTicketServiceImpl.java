package com.example.smartparking.service.impl;

import com.example.smartparking.repository.ParkingTicketRepository;
import com.example.smartparking.service.ParkingTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingTicketServiceImpl implements ParkingTicketService {

    private final ParkingTicketRepository parkingTicketRepository;
}

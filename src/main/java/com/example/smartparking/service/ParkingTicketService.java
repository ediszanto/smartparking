package com.example.smartparking.service;

import com.example.smartparking.model.ParkingTicket;
import com.example.smartparking.model.Reservation;

public interface ParkingTicketService {
    ParkingTicket parkWithReservation(String reservationNumber);

    ParkingTicket parkWithoutReservation(ParkingTicket parkingTicket);

    ParkingTicket pay(Long ticketId);
}

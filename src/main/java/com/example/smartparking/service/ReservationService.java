package com.example.smartparking.service;

import com.example.smartparking.model.Reservation;
import org.springframework.security.core.Authentication;

public interface ReservationService {

    Reservation makeReservation(Reservation reservationRequest, Authentication authentication);
}

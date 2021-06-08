package com.example.smartparking.service;

import com.example.smartparking.model.Reservation;
import javassist.NotFoundException;
import org.springframework.security.core.Authentication;

public interface ReservationService {

    Reservation saveReservation(Reservation reservationRequest, Authentication authentication);

    Reservation updateReservation(Long id, Reservation reservationUpdate);

    Reservation getReservationById(Long id);

    void cancelReservation(Long id);
}

package com.example.smartparking.service.impl;

import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.model.User;
import com.example.smartparking.repository.ParkingSpotRepository;
import com.example.smartparking.repository.ReservationRepository;
import com.example.smartparking.repository.UserRepository;
import com.example.smartparking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    @Override
    public Reservation makeReservation(Reservation reservationRequest, Authentication authentication) {
        reservationRequest.setIssuedAt(LocalDateTime.now());
        reservationRequest.setExpiresAt(reservationRequest.getStartTime().plusHours(4));
        reservationRequest.setReservationNumber(UUID.randomUUID());

         // ------ Get USER from Security Context ----- //
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        reservationRequest.setUser(user.get());

        String vehicleSize = reservationRequest.getVehicle().getSize();
        List<ParkingSpot> emptySpotsBySize = parkingSpotRepository.getEmptySpotBySize(reservationRequest.getVehicle().getSize(), "EMPTY");
        reservationRequest.setParkingSpot(emptySpotsBySize.get(0));

        return reservationRepository.save(reservationRequest);
    }
}

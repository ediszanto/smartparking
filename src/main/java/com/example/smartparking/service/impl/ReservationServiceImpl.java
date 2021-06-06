package com.example.smartparking.service.impl;

import com.example.smartparking.exceptions.NotFoundException;
import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.model.User;
import com.example.smartparking.repository.ParkingSpotRepository;
import com.example.smartparking.repository.ReservationRepository;
import com.example.smartparking.repository.UserRepository;
import com.example.smartparking.service.ParkingSpotService;
import com.example.smartparking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ParkingSpotService parkingSpotService;

    @Override
    public Reservation saveReservation(Reservation reservationRequest, Authentication authentication) {
        User user = null;
        if (Optional.ofNullable(userRepository.findByEmail(authentication.getName())).isEmpty()){
            throw new NotFoundException("User Not Found");
        }else{
            user = userRepository.findByEmail(authentication.getName()).get();
        }
        ParkingSpot desiredParkingSpot = parkingSpotService.findSpotByNumber(reservationRequest.getParkingSpot().getNumber());
        if(desiredParkingSpot.getStatus().equals("TAKEN") || !desiredParkingSpot.getSize().equals(reservationRequest.getVehicle().getSize())){
            throw new IllegalStateException("Parking Spot already taken Or size of vehicle and parking spot doesn't match!");
        }
        reservationRequest.setReservationNumber(UUID.randomUUID().toString());
        reservationRequest.setIssuedAt(LocalDate.now());
        reservationRequest.setExpiresAt(reservationRequest.getStartTime());
        reservationRequest.setParkingSpot(desiredParkingSpot);
        reservationRequest.setUser(user);
        return reservationRepository.save(reservationRequest);
    }

    @Override
    public Reservation getReservationByReservationById(Long id) {
        return reservationRepository.findReservationById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
    }

    @Override
    public void cancelReservation(Long id) throws NotFoundException {
        Reservation reservationToInvalidate = getReservationByReservationById(id);

        reservationToInvalidate.setExpiresAt(LocalDate.now());
        reservationToInvalidate.setEndTime(LocalDate.now());
        reservationRepository.save(reservationToInvalidate);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservationUpdate) {
        Reservation savedReservation = getReservationByReservationById(id);

        LocalDate limit = savedReservation.getStartTime().minusDays(7);
        if(LocalDate.now().isAfter(limit)){
            throw new IllegalStateException("Too Late. Last day to update is one week before start date");
        }
        savedReservation.setStartTime(Optional.ofNullable(reservationUpdate.getStartTime()).orElse(savedReservation.getStartTime()));
        savedReservation.setEndTime(Optional.ofNullable(reservationUpdate.getEndTime()).orElse(savedReservation.getEndTime()));
        return reservationRepository.save(savedReservation);
    }

}

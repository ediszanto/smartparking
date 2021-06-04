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
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingSpotService parkingSpotService;

    @Override
    public Reservation makeReservation(Reservation reservationRequest, Authentication authentication) {
        reservationRequest.setIssuedAt(LocalDate.now());
        reservationRequest.setExpiresAt(reservationRequest.getStartTime());
        reservationRequest.setReservationNumber(UUID.randomUUID().toString());

         //Get USER from Security Context
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        reservationRequest.setUser(user.get());

        // Verify if Parking spot is availble
        ParkingSpot desiredParkingSpot = parkingSpotService.findSpotByNumber(reservationRequest.getParkingSpot().getNumber());

//        TODO tre verificata conditia asta...ca nu stiu daca functioneaza cum trebuie:((((((
        if(!desiredParkingSpot.getStatus().equals("TAKEN") || !desiredParkingSpot.getSize().equals(reservationRequest.getVehicle().getSize())){
            throw new IllegalStateException("Parking Spot already taken Or size of vehicle and parking spot doesn't match!");
        }
        reservationRequest.setParkingSpot(desiredParkingSpot);

        return reservationRepository.save(reservationRequest);
    }

    @Override
    public Reservation getReservationByReservationById(Long id) {
        return reservationRepository.findReservationById(id).orElseThrow(() -> new NotFoundException("Reseration not found"));
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

        LocalDate timeNow =   LocalDate.now();
        LocalDate limit = savedReservation.getStartTime().minusDays(7);
        if(timeNow.isAfter(limit)){
            throw new IllegalStateException("Too Late. You are not allowed to update reservation after one wek before");
        }

        savedReservation.setStartTime(Optional.ofNullable(reservationUpdate.getStartTime()).orElse(savedReservation.getStartTime()));
        savedReservation.setEndTime(Optional.ofNullable(reservationUpdate.getEndTime()).orElse(savedReservation.getEndTime()));
        return reservationRepository.save(savedReservation);
    }

}

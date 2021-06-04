package com.example.smartparking.service.impl;

import com.example.smartparking.exceptions.NotFoundException;
import com.example.smartparking.model.ParkingFee;
import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.model.ParkingTicket;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.repository.ParkingFeeRepository;
import com.example.smartparking.repository.ParkingTicketRepository;
import com.example.smartparking.repository.ReservationRepository;
import com.example.smartparking.service.ParkingFeeService;
import com.example.smartparking.service.ParkingSpotService;
import com.example.smartparking.service.ParkingTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingTicketServiceImpl implements ParkingTicketService {

    private final ParkingTicketRepository parkingTicketRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationServiceImpl reservationService;
    private final ParkingSpotService parkingSpotService;
    private final ParkingFeeRepository parkingFeeRepository;
//    private final ParkingFeeService parkingFeeService;

    @Override
    public ParkingTicket parkWithReservation(String reservationNumber) {
        // vehicle , parking spot, parking spot status
        Reservation reservation = Optional.ofNullable(reservationRepository.findReservationByReservationNumber(reservationNumber)).orElseThrow(() -> new NotFoundException("reservation not found"));

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setParkingSpot(reservation.getParkingSpot());
        parkingTicket.setVehicle(reservation.getVehicle());
        parkingTicket.setStartTime(LocalDateTime.now());

        ParkingFee parkingFee = new ParkingFee();
        parkingFee.setParkingSpotSize(parkingTicket.getParkingSpot().getSize());
        parkingTicket.setParkingFee(parkingFee);

        reservationService.cancelReservation(reservation.getId());
        return parkingTicketRepository.save(parkingTicket);
    }

    @Override
    public ParkingTicket parkWithoutReservation(ParkingTicket parkingTicket) {
        // vehicle , parking spot, parking spot status
        ParkingSpot desiredParkingSpot = parkingSpotService.findSpotByNumber(parkingTicket.getParkingSpot().getNumber());
        if(desiredParkingSpot.getStatus().equals("TAKEN") || !desiredParkingSpot.getSize().equals(parkingTicket.getVehicle().getSize())){
            throw new IllegalStateException("Parking Spot already taken Or size of vehicle and parking spot doesn't match!");
        }
        desiredParkingSpot.setStatus("TAKEN");
        parkingSpotService.updateParkingSpot(desiredParkingSpot);

        parkingTicket.setParkingSpot(desiredParkingSpot);
        parkingTicket.setStartTime(LocalDateTime.now());

        return parkingTicketRepository.save(parkingTicket);
    }

    @Override
    public ParkingTicket pay(Long id) {
        ParkingTicket parkingTicket = Optional.ofNullable(parkingTicketRepository.getParkingTicketById(id)).orElseThrow(() -> new NotFoundException("ticked not found"));
        parkingTicket.setEndTime(LocalDateTime.now());
        return parkingTicketRepository.save(parkingTicket);
    }


}

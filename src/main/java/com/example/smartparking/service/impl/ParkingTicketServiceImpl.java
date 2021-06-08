package com.example.smartparking.service.impl;

import com.example.smartparking.exceptions.NotFoundException;
import com.example.smartparking.model.ParkingFee;
import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.model.ParkingTicket;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.payment.PaymentFactory;
import com.example.smartparking.repository.ParkingFeeRepository;
import com.example.smartparking.repository.ParkingTicketRepository;
import com.example.smartparking.repository.ReservationRepository;
import com.example.smartparking.service.ParkingFeeService;
import com.example.smartparking.service.ParkingSpotService;
import com.example.smartparking.service.ParkingTicketService;
import com.example.smartparking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingTicketServiceImpl implements ParkingTicketService {

    private final ParkingTicketRepository parkingTicketRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;
    private final ParkingSpotService parkingSpotService;

    @Override
    public ParkingTicket parkWithReservation(String reservationNumber) {
        // vehicle , parking spot, parking spot status
        Reservation reservation = Optional.ofNullable(reservationRepository.findReservationByReservationNumber(reservationNumber)).orElseThrow(() -> new NotFoundException("reservation not found"));
        ParkingSpot parkingSpot = reservation.getParkingSpot();

        parkingSpot.setStatus("TAKEN");
        parkingSpotService.updateParkingSpot(parkingSpot);

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setParkingSpot(parkingSpot);
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
        if(desiredParkingSpot.getStatus().equals("TAKEN") || !desiredParkingSpot.getSize().equals(parkingTicket.getVehicle().getSize()) ){
            throw new IllegalStateException("Parking Spot already taken Or size of vehicle and parking spot doesn't match!");
        }
        ParkingFee parkingFee = new ParkingFee();
        parkingFee.setParkingSpotSize(desiredParkingSpot.getSize());
        parkingTicket.setParkingSpot(desiredParkingSpot);
        parkingTicket.setVehicle(parkingTicket.getVehicle());
        parkingTicket.setStartTime(LocalDateTime.now());
        parkingTicket.setParkingFee(parkingFee);

        desiredParkingSpot.setStatus("TAKEN");
        parkingSpotService.updateParkingSpot(desiredParkingSpot);
        return parkingTicketRepository.save(parkingTicket);
    }

    @Override
    public ParkingTicket pay(Long id) {
        ParkingTicket parkingTicket = Optional.ofNullable(parkingTicketRepository.getParkingTicketById(id)).orElseThrow(() -> new NotFoundException("ticked not found"));
        parkingTicket.setEndTime(LocalDateTime.now());

        ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
        parkingSpot.setStatus("EMPTY");
        parkingSpotService.updateParkingSpot(parkingSpot);

        Duration duration = Duration.between(parkingTicket.getStartTime(), parkingTicket.getEndTime());
        String size = parkingTicket.getParkingSpot().getSize();
        Double amout = PaymentFactory.calculatePayment(size, duration);


        ParkingFee parkingFee = parkingTicket.getParkingFee();
        parkingFee.setAmount(amout);
        parkingTicket.setParkingFee(parkingFee);

        log.info("You jave to pay: $" + amout + " --- time to pay for: " + duration.toMinutes() + " minutes -- ( minutes < 60 => you have to pay a FULL hour)");

        return parkingTicketRepository.save(parkingTicket);
    }


}

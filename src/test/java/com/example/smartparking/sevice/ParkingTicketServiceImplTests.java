package com.example.smartparking.sevice;

import com.example.smartparking.model.*;
import com.example.smartparking.repository.ParkingTicketRepository;
import com.example.smartparking.repository.ReservationRepository;
import com.example.smartparking.service.ReservationService;
import com.example.smartparking.service.impl.ParkingSpotServiceImpl;
import com.example.smartparking.service.impl.ParkingTicketServiceImpl;
import com.example.smartparking.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingTicketServiceImplTests {

    @InjectMocks
    private ParkingTicketServiceImpl parkingTicketService;
    @Mock
    private ParkingSpotServiceImpl parkingSpotService;
    @Mock
    private ParkingTicketRepository parkingTicketRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private ReservationServiceImpl reservationService;


    @Test
    public void testParkWithReservation(){
        Reservation reservation = new Reservation();
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSize("M");
        parkingSpot.setNumber(1L);

        Vehicle vehicle = new Vehicle();
        vehicle.setSize("M");

        reservation.setParkingSpot(parkingSpot);
        reservation.setVehicle(vehicle);
        reservation.setReservationNumber("1");
        reservation.setId(1L);

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setParkingSpot(parkingSpot);
        parkingTicket.setVehicle(vehicle);
        parkingTicket.setId(1L);

        doReturn(reservation).when(reservationRepository).findReservationByReservationNumber("1");

        parkingTicketService.parkWithReservation("1");

        ArgumentCaptor<ParkingTicket> parkingTicketArgumentCaptor = ArgumentCaptor.forClass(ParkingTicket.class);
        verify(parkingTicketRepository, times(1)).save(parkingTicketArgumentCaptor.capture());
        ParkingTicket result = parkingTicketArgumentCaptor.getValue();
        assertNotNull(result);
        assertTrue(result.getParkingFee().getParkingSpotSize().equals("M"));

    }

    @Test
    public void testParkWithoutReservation(){
        ParkingSpot parkingSpotUpdated = new ParkingSpot();
        parkingSpotUpdated.setSize("M");
        parkingSpotUpdated.setNumber(1L);
        parkingSpotUpdated.setStatus("TAKEN");

        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSize("M");
        parkingSpot.setNumber(1L);
        parkingSpot.setStatus("EMPTY");

        Vehicle vehicle = new Vehicle();
        vehicle.setSize("M");

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setParkingSpot(parkingSpot);
        parkingTicket.setVehicle(vehicle);
        parkingTicket.setId(1L);

        doReturn(parkingSpot).when(parkingSpotService).findSpotByNumber(1L);

        parkingTicketService.parkWithoutReservation(parkingTicket);

        ArgumentCaptor<ParkingTicket> parkingTicketArgumentCaptor = ArgumentCaptor.forClass(ParkingTicket.class);
        verify(parkingTicketRepository, times(1)).save(parkingTicketArgumentCaptor.capture());
        ParkingTicket result = parkingTicketArgumentCaptor.getValue();
        assertNotNull(result);
        assertTrue(result.getParkingFee().getParkingSpotSize().equals("M"));
    }

    @Test
    public void testPay(){
        Long id = 1L;
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setNumber(id);
        parkingSpot.setSize("M");
        parkingSpot.setStatus("EMPTY");

        ParkingFee parkingFee = new ParkingFee();
        parkingFee.setParkingSpotSize("M");

        Vehicle vehicle = new Vehicle();
        vehicle.setSize("M");

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setStartTime(LocalDateTime.now().minusHours(3));
        parkingTicket.setEndTime(LocalDateTime.now());
        parkingTicket.setParkingSpot(parkingSpot);
        parkingTicket.setVehicle(vehicle);
        parkingTicket.setId(id);
        parkingTicket.setParkingFee(parkingFee);

        doReturn(parkingTicket).when(parkingTicketRepository).getParkingTicketById(id);

        parkingTicketService.pay(id);

        ArgumentCaptor<ParkingTicket> parkingTicketArgumentCaptor = ArgumentCaptor.forClass(ParkingTicket.class);
        verify(parkingTicketRepository, times(1)).save(parkingTicketArgumentCaptor.capture());
        ParkingTicket result = parkingTicketArgumentCaptor.getValue();

        assertNotNull(result);
        assertDoesNotThrow(()->result.getParkingFee().getParkingSpotSize());
    }

}

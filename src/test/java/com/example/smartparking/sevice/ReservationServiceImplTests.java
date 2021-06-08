package com.example.smartparking.sevice;

import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.model.Reservation;
import com.example.smartparking.model.User;
import com.example.smartparking.repository.ReservationRepository;
import com.example.smartparking.repository.UserRepository;
import com.example.smartparking.service.ParkingSpotService;
import com.example.smartparking.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTests {
    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private ParkingSpotService parkingSpotService;

    @Test
    @Disabled
    public void ShouldReturnSavedReservation(){

        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setStatus("EMPTY");
        parkingSpot.setSize("M");
        User user = new User();
        user.setEmail("test@gmail.com");
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(UUID.randomUUID().toString());
        reservation.setUser(user);
        reservation.setParkingSpot(parkingSpot);

//        nu stiu cum sa simulez security context-ul
//        reservationService.saveReservation(reservation, authentication);


        ArgumentCaptor<Reservation> reservationArgumentCaptor = ArgumentCaptor.forClass(Reservation.class);
        verify(userRepository, times(2));
        verify(parkingSpotService, times(1));
        Reservation dbReservation = reservationArgumentCaptor.getValue();

        assertNotNull(dbReservation);
        assertEquals(reservation.getUser().getEmail(), dbReservation.getUser().getEmail());
    }

    @Test
    void shouldReturnReservationByGivenId(){
        Long id = 1L;
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setStatus("EMPTY");
        parkingSpot.setSize("M");
        User user = new User();
        user.setEmail("test@gmail.com");
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(UUID.randomUUID().toString());
        reservation.setUser(user);
        reservation.setParkingSpot(parkingSpot);
        reservation.setId(1L);

        when(reservationRepository.findReservationById(id)).thenReturn(Optional.ofNullable(reservation));
        Reservation result = reservationService.getReservationById(id);

        assertNotNull(result);
        assertEquals(id, reservation.getId());
    }

    @Test
    public void testCancelRequest(){
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setEndTime(LocalDate.now().minusDays(1));
        reservation.setExpiresAt(LocalDate.now().plusDays(2));

        doReturn(Optional.ofNullable(reservation)).when(reservationRepository).findReservationById(1L);
        reservationService.cancelReservation(1L);

        ArgumentCaptor<Reservation> reservationArgumentCaptor = ArgumentCaptor.forClass(Reservation.class);
        verify(reservationRepository, times(1)).save(reservationArgumentCaptor.capture());
        Reservation resultReservation = reservationArgumentCaptor.getValue();
        assertNotNull(resultReservation);
        assertEquals(resultReservation.getEndTime(), LocalDate.now());
        assertEquals(resultReservation.getExpiresAt(), LocalDate.now());
    }

    @Test
    public void testUpdateReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setStartTime(LocalDate.now().plusDays(8));
        reservation.setEndTime(LocalDate.now().plusDays(9));

        Reservation reservationUpdate = new Reservation();
        reservationUpdate.setId(1L);
        reservationUpdate.setStartTime(LocalDate.now().plusDays(10));
        reservationUpdate.setEndTime(LocalDate.now().plusDays(11));

        doReturn(Optional.ofNullable(reservation)).when(reservationRepository).findReservationById(1L);
        reservationService.updateReservation(1l, reservationUpdate);

        ArgumentCaptor<Reservation> reservationArgumentCaptor = ArgumentCaptor.forClass(Reservation.class);
        verify(reservationRepository, times(1)).save(reservationArgumentCaptor.capture());
        Reservation result = reservationArgumentCaptor.getValue();
        assertNotNull(result);
        assertEquals(result.getStartTime(), reservationUpdate.getStartTime());
        assertEquals(result.getEndTime(), reservationUpdate.getEndTime());
    }

}

package com.example.smartparking.sevice;

import com.example.smartparking.model.ParkingSpot;
import com.example.smartparking.repository.ParkingSpotRepository;
import com.example.smartparking.service.impl.ParkingSpotServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotServiceImplTests {

    @InjectMocks
    private ParkingSpotServiceImpl parkingSpotService;

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @Test
    void shouldReturnCreatedParkingSpot(){
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSize("M");
        parkingSpot.setNumber(1L);
        parkingSpot.setStatus("EMPTY");

        doReturn(Optional.empty()).when(parkingSpotRepository).getByNumber(1L);

        parkingSpotService.saveParkingSpot(parkingSpot);

        ArgumentCaptor<ParkingSpot> parkingSpotArgumentCaptor = ArgumentCaptor.forClass(ParkingSpot.class);
        verify(parkingSpotRepository, times(1)).save(parkingSpotArgumentCaptor.capture());
        ParkingSpot dbParkingSpot = parkingSpotArgumentCaptor.getValue();
        assertNotNull(dbParkingSpot);
        assertTrue(dbParkingSpot.getNumber().equals(1L));
        assertTrue(dbParkingSpot.getStatus().equals("EMPTY"));
        assertTrue(dbParkingSpot.getSize().equals("M"));
    }

    @Test
    public  void shouldReturnSpotForGivenSpotNumber(){
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSize("M");
        parkingSpot.setNumber(1L);
        parkingSpot.setStatus("EMPTY");

        when(parkingSpotRepository.getByNumber(1L)).thenReturn(Optional.ofNullable(parkingSpot));

        ParkingSpot result = parkingSpotService.findSpotByNumber(1L);
        assertNotNull(result);
        assertTrue(result.getNumber().equals(1L));
        assertTrue(result.getStatus().equals("EMPTY"));
        assertTrue(result.getSize().equals("M"));
    }

    @Test
    public  void shouldReturnSpotForGivenSpotId(){
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setId(1L);
        parkingSpot.setSize("M");
        parkingSpot.setNumber(1L);
        parkingSpot.setStatus("EMPTY");

        when(parkingSpotRepository.findById(1L)).thenReturn(Optional.ofNullable(parkingSpot));

        ParkingSpot result = parkingSpotService.findSpotById(1L);
        assertNotNull(result);
        assertTrue(result.getNumber().equals(1L));
        assertTrue(result.getStatus().equals("EMPTY"));
        assertTrue(result.getSize().equals("M"));
    }

    @Test
    public  void testDeleteSpotByNumber(){
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSize("M");
        parkingSpot.setNumber(1L);
        parkingSpot.setStatus("EMPTY");

        when(parkingSpotRepository.getByNumber(1L)).thenReturn(Optional.ofNullable(parkingSpot));

        parkingSpotService.deleteSpotByNumber(1L);

        ArgumentCaptor<ParkingSpot> parkingSpotArgumentCaptor = ArgumentCaptor.forClass(ParkingSpot.class);
        verify(parkingSpotRepository, times(1)).delete(parkingSpotArgumentCaptor.capture());

        ParkingSpot result = parkingSpotArgumentCaptor.getValue();
        assertNotNull(result);
        assertTrue(result.getNumber().equals(1L));
        assertTrue(result.getStatus().equals("EMPTY"));
        assertTrue(result.getSize().equals("M"));
    }

    @Test
    public  void shouldReturnAllSpots(){
        List<ParkingSpot> dbSpots = new ArrayList<>();
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setId(1L);
        parkingSpot.setSize("M");
        parkingSpot.setNumber(1L);
        parkingSpot.setStatus("EMPTY");
        dbSpots.add(parkingSpot);

        when(parkingSpotRepository.findAll()).thenReturn(dbSpots);

        List<ParkingSpot> result = parkingSpotService.getAllSpots();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getNumber().equals(1L));
        assertTrue(result.get(0).getStatus().equals("EMPTY"));
        assertTrue(result.get(0).getSize().equals("M"));
    }

    @Test
    public void shouldReturnUpdatedParkingSpot(){
        ParkingSpot dbParkingSpot = new ParkingSpot();
        dbParkingSpot.setSize("M");
        dbParkingSpot.setNumber(1L);
        dbParkingSpot.setStatus("EMPTY");

        ParkingSpot updatedParkingSpot = new ParkingSpot();
        updatedParkingSpot.setSize("S");
        updatedParkingSpot.setNumber(1L);
        updatedParkingSpot.setStatus("TAKEN");

        doReturn(Optional.ofNullable(dbParkingSpot)).when(parkingSpotRepository).getByNumber(1L);
        parkingSpotService.updateParkingSpot(updatedParkingSpot);

        ArgumentCaptor<ParkingSpot> parkingSpotArgumentCaptor = ArgumentCaptor.forClass(ParkingSpot.class);
        verify(parkingSpotRepository, times(1)).save(parkingSpotArgumentCaptor.capture());
        ParkingSpot result = parkingSpotArgumentCaptor.getValue();
        assertNotNull(result);
        assertEquals("S", result.getSize());
        assertEquals("TAKEN", result.getStatus());
    }

}

package com.example.smartparking.sevice;

import com.example.smartparking.model.ParkingFee;
import com.example.smartparking.repository.ParkingFeeRepository;
import com.example.smartparking.service.impl.ParkingFeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingFeeImplTests {

    @InjectMocks
    private ParkingFeeServiceImpl parkingFeeService;

    @Mock
    private ParkingFeeRepository parkingFeeRepository;


    @Test
    public void testUpdateParkingFee(){
        Long id = 1L;
        ParkingFee dbParkingFee = new ParkingFee();
        dbParkingFee.setId(id);
        dbParkingFee.setParkingSpotSize("M");

        ParkingFee parkingFeeUpdated = new ParkingFee();
        parkingFeeUpdated.setId(id);
        parkingFeeUpdated.setAmount(20.0);

        doReturn(dbParkingFee).when(parkingFeeRepository).getParkingFeeById(id);

        parkingFeeService.updateParkingFee(id, parkingFeeUpdated);

        ArgumentCaptor<ParkingFee> parkingFeeArgumentCaptor = ArgumentCaptor.forClass(ParkingFee.class);
        verify(parkingFeeRepository, times(1)).save(parkingFeeArgumentCaptor.capture());

        ParkingFee result = parkingFeeArgumentCaptor.getValue();

        assertNotNull(result);
        assertEquals(20.0, result.getAmount());
        assertEquals("M", result.getParkingSpotSize());
    }

    @Test
    public void souldReturnParkingFeeForGivenId(){
        Long id = 1L;
        ParkingFee dbParkingFee = new ParkingFee();
        dbParkingFee.setId(id);
        dbParkingFee.setParkingSpotSize("M");

        when(parkingFeeRepository.getParkingFeeById(id)).thenReturn(dbParkingFee);
        ParkingFee result = parkingFeeService.findById(id);

        assertNotNull(result);
        assertEquals("M", result.getParkingSpotSize());


    }
}

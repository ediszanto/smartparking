package com.example.smartparking.repository;

import com.example.smartparking.model.ParkingFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingFeeRepository extends JpaRepository<ParkingFee, Long> {

    ParkingFee getParkingFeeById(Long id);

}

package com.example.smartparking.repository;

import com.example.smartparking.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    // Queries

    @Query("select s from ParkingSpot s where s.id=:id")
    Optional<ParkingSpot> findById(Long id);

    @Query("select s from ParkingSpot s")
    List<ParkingSpot> findAll();

    @Query("select s from ParkingSpot s where s.number=:spotNumber")
    Optional<ParkingSpot> getByNumber(Long spotNumber);

    @Query("select s from ParkingSpot s where s.status=:spotStatus")
    List<ParkingSpot> getParkingSpotByStatusSpots(String spotStatus);

    @Query(" select distinct p from ParkingSpot p where p.size=:size and p.status=:status")
    List<ParkingSpot> getEmptySpotBySize(String size, String status);
}

package com.example.smartparking.repository;

import com.example.smartparking.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    @Query("select r from Reservation r where r.id=:id")
    Optional<Reservation> findReservationById(Long id);

    Reservation findReservationByReservationNumber(String reservationNumber);
}

package com.example.reservation.repository;

import com.example.reservation.entity.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Long> {
    Optional<ReservationHistory> findByCreatedDate(LocalDate createdDate);
}

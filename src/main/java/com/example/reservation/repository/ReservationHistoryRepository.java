package com.example.reservation.repository;

import com.example.reservation.entity.Lesson;
import com.example.reservation.entity.Parent;
import com.example.reservation.entity.ReservationHistory;
import com.example.reservation.enumeration.ReservationStatus;
import com.example.reservation.repository.querydsl.ReservationHistoryQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Long>, ReservationHistoryQueryDslRepository {
    Boolean existsByParentAndLessonAndReservationDateAndReservationStatus(Parent parent, Lesson lesson, LocalDate createDate, ReservationStatus reservationStatus);
    List<ReservationHistory> findByLessonAndReservationDateAndReservationStatus(Lesson lesson, LocalDate reservationDate, ReservationStatus reservationStatus);
}
package com.example.reservation.repository;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.dto.ReservationHistoryDto;
import com.example.reservation.entity.Lesson;
import com.example.reservation.entity.Parent;
import com.example.reservation.entity.ReservationHistory;
import com.example.reservation.repository.querydsl.ReservationHistoryQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Long>, ReservationHistoryQueryDslRepository {
    Boolean existsByParentAndLessonAndReservationDate(Parent parent, Lesson lesson, LocalDate createDate);;

    @Query(value = "select " +
            "new com.example.reservation.dto.ReservationHistoryDto(" +
            "l.store.storeName, l.lessonName, p.parentName, p.email, rh.capacity, rh.reservationDate) " +
            "from ReservationHistory rh " +
            "join rh.parent p " +
            "join rh.lesson l " +
            "where p.parentId = :parentId")
    List<ReservationHistoryDto> findAllByParentId(@Param("parentId") long parentId);
}
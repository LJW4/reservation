package com.example.reservation.repository.querydsl;

import com.example.reservation.dto.ReservationDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationHistoryQueryDslRepository {
    List<ReservationDto.Response.ReservationHistory> findAllMadeByQueryDsl(Long parentId, Long storeId, Long lessonId, LocalDate reservationDate);
}

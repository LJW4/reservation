package com.example.reservation.repository.querydsl;

import com.example.reservation.dto.ReservationHistoryDto;
import com.example.reservation.dto.request.ParentHistoryDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationHistoryQueryDslRepository {
    List<ReservationHistoryDto> findAllMadeByQueryDsl(Long parentId, Long lessonId, LocalDate reservationDate);
}

package com.example.reservation;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.repository.ReservationHistoryRepository;
import com.example.reservation.service.ReservationHistoryService;
import com.example.reservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
public class ReservationTests {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationHistoryService reservationHistoryService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void save() {
        ReservationDto.Request.LessonReservation lessonReservationDto = ReservationDto.Request.LessonReservation.builder()
                .parentId(1L)
                .capacity(3)
                .lessonId(5L)
                .reservationDate(LocalDate.of(2023, 11, 3))
                .build();
        reservationService.lessonReservation(lessonReservationDto);
    }

    @Test
    @Transactional
    public void historiesByParent() {
        reservationHistoryService.findHistoriesByParent(1L, null, null);
    }

    @Test
    @Transactional
    public void historiesByStore() {
        reservationHistoryService.findHistoriesByStore(1L, null, null);
    }
}

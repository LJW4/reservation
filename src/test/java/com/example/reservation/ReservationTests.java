package com.example.reservation;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.repository.ReservationHistoryRepository;
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
    private ReservationHistoryRepository reservationHistoryRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void save() {
        ReservationDto.LessonReservation lessonReservationDto = ReservationDto.LessonReservation.builder()
                .parentId(1L)
                .capacity(3)
                .storeId(1L)
                .lessonId(2L)
                .reservationDate(LocalDate.of(2023, 11, 1))
                .build();
        reservationService.lessonReservation(lessonReservationDto);
    }

    @Test
    @Transactional
    public void history() {
        reservationHistoryRepository.findAllByParentId(1L);
    }
}

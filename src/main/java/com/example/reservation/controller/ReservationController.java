package com.example.reservation.controller;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/api/lesson/reservation")
    public ResponseEntity<String> lessonReservation(@RequestBody ReservationDto.Request.LessonReservation lessonReservationDto) {
        reservationService.lessonReservation(lessonReservationDto);
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }
}

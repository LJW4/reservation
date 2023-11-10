package com.example.reservation.controller;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/api/reservation")
    public ResponseEntity<String> reservation(@RequestBody ReservationDto.Request.LessonReservation lessonReservationDto) {
        reservationService.reservation(lessonReservationDto);
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }

    @PatchMapping("/api/reservation/{reservationId}/cancel")
    public ResponseEntity<String> reservationCancel(@PathVariable("reservationId") long reservationId) {
        reservationService.reservationCancel(reservationId);
        return ResponseEntity.ok("예약을 취소하였습니다.");
    }
}

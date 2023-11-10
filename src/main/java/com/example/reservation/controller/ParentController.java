package com.example.reservation.controller;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.service.ReservationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParentController {
    private final ReservationHistoryService reservationHistoryService;

    @GetMapping("/api/parent/{parentId}/reservation-histories")
    public ResponseEntity<List<ReservationDto.Response.ReservationHistory>> findReservationHistories(
            @PathVariable("parentId") Long parentId,
            @RequestParam(value = "storeId", required = false) Long storeId,
            @RequestParam(value = "lessonId", required = false) Long lessonId,
            @RequestParam(value = "reservationDate", required = false) LocalDate reservationDate
    ) {
        return ResponseEntity.ok(reservationHistoryService.findHistoriesByParent(parentId, storeId, lessonId, reservationDate));
    }
}

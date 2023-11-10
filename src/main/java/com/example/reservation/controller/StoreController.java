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
@RequestMapping("/api/store")
public class StoreController {
    private final ReservationHistoryService reservationHistoryService;

    @GetMapping("/api/store/{storeId}/reservation-histories")
    public ResponseEntity<List<ReservationDto.Response.ReservationHistory>> findReservationHistories(
            @PathVariable("storeId") Long storeId,
            @RequestParam(value = "lessonId", required = false) Long lessonId,
            @RequestParam(value = "reservationDate", required = false) LocalDate reservationDate
    ) {
        return ResponseEntity.ok(reservationHistoryService.findHistoriesByStore(storeId, lessonId, reservationDate));
    }
}

package com.example.reservation.controller;

import com.example.reservation.dto.ReservationHistoryDto;
import com.example.reservation.service.ReservationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parent")
public class ParentController {
    private final ReservationHistoryService reservationHistoryService;

    @GetMapping("/{parentId}/reservation-histories")
    public ResponseEntity<List<ReservationHistoryDto>> findHistoriesByParentId(
            @PathVariable("parentId") Long parentId,
            @RequestParam(value = "lessonId", required = false) Long lessonId,
            @RequestParam(value = "reservationDate", required = false) LocalDate reservationDate
    ) {
        return ResponseEntity.ok(reservationHistoryService.findHistoriesByParentId(parentId, lessonId, reservationDate));
    }
}

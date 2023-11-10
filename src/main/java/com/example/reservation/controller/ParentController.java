package com.example.reservation.controller;

import com.example.reservation.dto.ReservationHistoryDto;
import com.example.reservation.service.ReservationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parent")
public class ParentController {
    private final ReservationHistoryService reservationHistoryService;

    @GetMapping("/{parentId}/reservation-histories")
    public ResponseEntity<List<ReservationHistoryDto>> findHistoriesByParentId(@PathVariable("parentId") long parentId) {
        return ResponseEntity.ok(reservationHistoryService.findHistoriesByParentId(parentId));
    }
}

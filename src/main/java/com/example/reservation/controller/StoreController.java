package com.example.reservation.controller;

import com.example.reservation.service.ReservationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {
    private final ReservationHistoryService reservationHistoryService;
}
